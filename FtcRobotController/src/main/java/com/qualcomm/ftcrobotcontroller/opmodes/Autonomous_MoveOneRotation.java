package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Declan Freeman-Gleason on 11/15/2015.
 */
public class Autonomous_MoveOneRotation extends OpMode {
    DcMotor MotorRight_F;
    DcMotor MotorLeft_F;
    DcMotor MotorRight_B;
    DcMotor MotorLeft_B;
    final int PPM = 1120;
    @Override
    public void init() {
        MotorRight_F = hardwareMap.dcMotor.get("RightMotorF");
        MotorLeft_F = hardwareMap.dcMotor.get("LeftMotorF");
        MotorRight_B = hardwareMap.dcMotor.get("RightMotorB");
        MotorLeft_B = hardwareMap.dcMotor.get("LeftMotorB");
        MotorRight_F.setDirection(DcMotor.Direction.REVERSE);
        MotorRight_B.setDirection(DcMotor.Direction.REVERSE);
        MotorLeft_F.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        MotorRight_F.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        MotorLeft_B.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        MotorRight_B.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        // Set Motor Target Positions
        MotorRight_F.setTargetPosition(-PPM);
        MotorLeft_F.setTargetPosition(PPM);
        MotorRight_B.setTargetPosition(-PPM);
        MotorLeft_B.setTargetPosition(PPM);
    }

    @Override
    public void loop() {
        // Set Motor Power
        MotorRight_F.setPower(0.25);
        MotorLeft_F.setPower(0.25);
        MotorRight_B.setPower(0.25);
        MotorLeft_B.setPower(0.25);
        telemetry.addData("Position", MotorRight_F.getCurrentPosition());
        if (Math.abs(MotorLeft_F.getCurrentPosition() + MotorRight_F.getCurrentPosition() + MotorRight_F.getCurrentPosition() + MotorRight_B.getCurrentPosition()) >= PPM * 4) {
            MotorLeft_F.setPower(0);
            MotorRight_F.setPower(0);
            MotorLeft_B.setPower(0);
            MotorRight_B.setPower(0);
        }
    }
}
