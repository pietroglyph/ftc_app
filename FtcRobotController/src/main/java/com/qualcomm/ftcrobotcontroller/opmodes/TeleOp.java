package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Declan Freeman-Gleason on 11/4/2015.
 */
public class TeleOp extends OpMode {
    Servo servo1;
    Servo servo2;
    DcMotor MotorRight_F;
    DcMotor MotorLeft_F;
    DcMotor MotorRight_B;
    DcMotor MotorLeft_B;
    @Override
    public void init() {
        MotorRight_F = hardwareMap.dcMotor.get("RightMotorF");
        MotorLeft_F = hardwareMap.dcMotor.get("LeftMotorF");
        MotorRight_B = hardwareMap.dcMotor.get("RightMotorB");
        MotorLeft_B = hardwareMap.dcMotor.get("LeftMotorB");
        MotorRight_F.setDirection(DcMotor.Direction.REVERSE);
        MotorRight_B.setDirection(DcMotor.Direction.REVERSE);
        MotorLeft_F.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        MotorRight_F.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        MotorLeft_B.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        MotorRight_B.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        servo1 = hardwareMap.servo.get("servo1");
        servo2 = hardwareMap.servo.get("servo2");
        servo1.setPosition(-1);
    }

    @Override
    public void loop() {
        float rightMotor_F = 0;
        float rightMotor_B = 0;
        float leftMotor_F = 0;
        float leftMotor_B = 0;
        float leftTrigger = 0;
        float rightTrigger = 0;
        Range.clip(rightMotor_F, -1, 1);
        Range.clip(rightMotor_B, -1, 1);
        Range.clip(leftMotor_F, -1, 1);
        Range.clip(leftMotor_B, -1, 1);
        Range.clip(leftTrigger, -1,1);
        Range.clip(rightTrigger, -1, 1);
        leftMotor_F = gamepad1.left_stick_y;
        leftMotor_B = gamepad1.left_stick_y;
        rightMotor_F = gamepad1.right_stick_y;
        rightMotor_B = gamepad1.right_stick_y;
        telemetry.addData("Stick / Motor Values Before Modification", leftMotor_F);
        if (!gamepad1.left_bumper) {
            leftMotor_F = leftMotor_F / 3;
            leftMotor_B = leftMotor_B / 3;
        }
        if (!gamepad1.right_bumper) {
            rightMotor_F = rightMotor_F / 3;
            rightMotor_B = rightMotor_B / 3;
        }
        telemetry.addData("Stick / Motor Values After Modification", leftMotor_F);
//        telemetry.addData("Motor Values", "Motor Left Front: " + MotorLeft_F + ", Motor Left Back: " + MotorLeft_B + ", Motor Right Front: " + MotorRight_F + ", Motor Right Back: " + MotorRight_B);
        MotorRight_F.setPower(rightMotor_F);
        MotorRight_B.setPower(rightMotor_B);
        MotorLeft_F.setPower(leftMotor_F);
        MotorLeft_B.setPower(leftMotor_B);
        leftTrigger = gamepad1.left_trigger;
        servo1.setPosition(leftTrigger);
        rightTrigger = gamepad1.right_trigger;
        servo2.setPosition(rightTrigger);
    }
}
