package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.ArrayList;
import java.util.List;

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to a LEGO MINDSTORMS NXT robot, with functions that can move and turn the robot.", iconName = "images/legoMindstormsNxt.png", nonVisible = true, version = 1)
@SimpleObject
/* loaded from: classes.dex */
public class NxtDrive extends LegoMindstormsNxtBase {
    private static final int MODE_BRAKE = 2;
    private static final int MODE_MOTORON = 1;
    private static final int MODE_REGULATED = 4;
    private static final int MOTOR_RUN_STATE_IDLE = 0;
    private static final int MOTOR_RUN_STATE_RAMPDOWN = 64;
    private static final int MOTOR_RUN_STATE_RAMPUP = 16;
    private static final int MOTOR_RUN_STATE_RUNNING = 32;
    private static final int REGULATION_MODE_IDLE = 0;
    private static final int REGULATION_MODE_MOTOR_SPEED = 1;
    private static final int REGULATION_MODE_MOTOR_SYNC = 2;
    private List<Integer> driveMotorPorts;
    private String driveMotors;
    private boolean stopBeforeDisconnect;
    private double wheelDiameter;

    public NxtDrive(ComponentContainer container) {
        super(container, "NxtDrive");
        DriveMotors("CB");
        WheelDiameter(4.32f);
        StopBeforeDisconnect(true);
    }

    @Override // com.google.appinventor.components.runtime.LegoMindstormsNxtBase, com.google.appinventor.components.runtime.BluetoothConnectionListener
    public void beforeDisconnect(BluetoothConnectionBase bluetoothConnection) {
        if (this.stopBeforeDisconnect) {
            for (Integer num : this.driveMotorPorts) {
                int port = num.intValue();
                setOutputState("Disconnect", port, 0, 2, 0, 0, 0, 0L);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The motor ports that are used for driving: the left wheel's motor port followed by the right wheel's motor port.", userVisible = false)
    public String DriveMotors() {
        return this.driveMotors;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "CB", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void DriveMotors(String motorPortLetters) {
        this.driveMotors = motorPortLetters;
        this.driveMotorPorts = new ArrayList();
        for (int i = 0; i < motorPortLetters.length(); i++) {
            char ch = motorPortLetters.charAt(i);
            try {
                this.driveMotorPorts.add(Integer.valueOf(convertMotorPortLetterToNumber(ch)));
            } catch (IllegalArgumentException e) {
                this.form.dispatchErrorOccurredEvent(this, "DriveMotors", ErrorMessages.ERROR_NXT_INVALID_MOTOR_PORT, Character.valueOf(ch));
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The diameter of the wheels used for driving.", userVisible = false)
    public float WheelDiameter() {
        return (float) this.wheelDiameter;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "4.32", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void WheelDiameter(float wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether to stop the drive motors before disconnecting.")
    public boolean StopBeforeDisconnect() {
        return this.stopBeforeDisconnect;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void StopBeforeDisconnect(boolean stopBeforeDisconnect) {
        this.stopBeforeDisconnect = stopBeforeDisconnect;
    }

    @SimpleFunction(description = "Move the robot forward indefinitely, with the specified percentage of maximum power, by powering both drive motors forward.")
    public void MoveForwardIndefinitely(int power) {
        move("MoveForwardIndefinitely", power, 0L);
    }

    @SimpleFunction(description = "Move the robot backward indefinitely, with the specified percentage of maximum power, by powering both drive motors backward.")
    public void MoveBackwardIndefinitely(int power) {
        move("MoveBackwardIndefinitely", -power, 0L);
    }

    @SimpleFunction(description = "Move the robot forward the given distance, with the specified percentage of maximum power, by powering both drive motors forward.")
    public void MoveForward(int power, double distance) {
        long tachoLimit = (long) ((360.0d * distance) / (this.wheelDiameter * 3.141592653589793d));
        move("MoveForward", power, tachoLimit);
    }

    @SimpleFunction(description = "Move the robot backward the given distance, with the specified percentage of maximum power, by powering both drive motors backward.")
    public void MoveBackward(int power, double distance) {
        long tachoLimit = (long) ((360.0d * distance) / (this.wheelDiameter * 3.141592653589793d));
        move("MoveBackward", -power, tachoLimit);
    }

    private void move(String functionName, int power, long tachoLimit) {
        if (checkBluetooth(functionName)) {
            for (Integer num : this.driveMotorPorts) {
                int port = num.intValue();
                setOutputState(functionName, port, power, 1, 1, 0, 32, tachoLimit);
            }
        }
    }

    @SimpleFunction(description = "Turn the robot clockwise indefinitely, with the specified percentage of maximum power, by powering the left drive motor forward and the right drive motor backward.")
    public void TurnClockwiseIndefinitely(int power) {
        int numDriveMotors = this.driveMotorPorts.size();
        if (numDriveMotors >= 2) {
            int backwardMotorIndex = numDriveMotors - 1;
            turnIndefinitely("TurnClockwiseIndefinitely", power, 0, backwardMotorIndex);
        }
    }

    @SimpleFunction(description = "Turn the robot counterclockwise indefinitely, with the specified percentage of maximum power, by powering the right drive motor forward and the left drive motor backward.")
    public void TurnCounterClockwiseIndefinitely(int power) {
        int numDriveMotors = this.driveMotorPorts.size();
        if (numDriveMotors >= 2) {
            int forwardMotorIndex = numDriveMotors - 1;
            turnIndefinitely("TurnCounterClockwiseIndefinitely", power, forwardMotorIndex, 0);
        }
    }

    private void turnIndefinitely(String functionName, int power, int forwardMotorIndex, int reverseMotorIndex) {
        if (checkBluetooth(functionName)) {
            setOutputState(functionName, this.driveMotorPorts.get(forwardMotorIndex).intValue(), power, 1, 1, 0, 32, 0L);
            setOutputState(functionName, this.driveMotorPorts.get(reverseMotorIndex).intValue(), -power, 1, 1, 0, 32, 0L);
        }
    }

    @SimpleFunction(description = "Stop the drive motors of the robot.")
    public void Stop() {
        if (checkBluetooth("Stop")) {
            for (Integer num : this.driveMotorPorts) {
                int port = num.intValue();
                setOutputState("Stop", port, 0, 2, 0, 0, 0, 0L);
            }
        }
    }
}