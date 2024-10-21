package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp

public class MainTeleopdrive extends LinearOpMode {

    DcMotor driveFrontleft = null; //Control hub port 0
    DcMotorEx driveFrontleftEx = null;
    DcMotor driveFrontright = null; //Exp hub port 0
    DcMotorEx driveFrontrightEx = null;
    DcMotor driveBackleft = null; //Control hub port 1
    DcMotorEx driveBackleftEx = null;
    DcMotor driveBackright = null; //Exp hub port 1
    DcMotorEx driveBackrightEx = null;
    DcMotor slideLeft = null; //control hub port 2
    DcMotorEx slideLeftEx = null;
    DcMotor slideRight = null; //Exp hub port 2
    DcMotorEx slideRightEx = null;

    DcMotor motorWench = null;
    DcMotorEx motorWenchEx = null;


    CRServo servoIntakeLeft = null;
    CRServo servoIntakeRight = null;
    Servo servoSlideLeft = null;
    Servo servoSlideRight = null;
    Servo servoWristLeft = null;
    Servo servoWristRight = null;
    Servo servoHookLeft = null;
    Servo servoHookRight = null;


    @Override
    public void runOpMode() throws InterruptedException {

        driveFrontleft = hardwareMap.dcMotor.get("driveFrontleft");
        driveFrontleftEx = (DcMotorEx) driveFrontleft;
        driveFrontright = hardwareMap.dcMotor.get("driveFrontright");
        driveFrontrightEx = (DcMotorEx) driveFrontright;
        driveBackleft = hardwareMap.dcMotor.get("driveBackleft");
        driveBackleftEx = (DcMotorEx) driveBackleft;
        driveBackright = hardwareMap.dcMotor.get("driveBackright");
        driveBackrightEx = (DcMotorEx) driveBackright;
        slideRight = hardwareMap.dcMotor.get("slideRight");
        slideRightEx = (DcMotorEx) slideRight;
        slideLeft = hardwareMap.dcMotor.get("slideLeft");
        slideLeftEx = (DcMotorEx) slideLeft;
        motorWench = hardwareMap.dcMotor.get("motorWench");
        motorWenchEx = (DcMotorEx) motorWench;
        servoIntakeLeft = hardwareMap.get(CRServo.class, "servoIntakeLeft");
        servoIntakeRight = hardwareMap.get(CRServo.class, "servoIntakeRight");
        servoSlideLeft = hardwareMap.get(Servo.class, "servoSlideLeft");
        servoSlideRight = hardwareMap.get(Servo.class, "servoSlideRight");
        servoWristLeft = hardwareMap.get(Servo.class, "servoWristLeft");
        servoWristRight = hardwareMap.get(Servo.class, "servoWristRight");
        servoHookLeft = hardwareMap.get(Servo.class, "servoHookLeft");
        servoHookRight = hardwareMap.get(Servo.class, "servoHookRight");


        driveFrontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driveFrontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driveBackleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driveBackright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        slidePivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        driveFrontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        driveBackleft.setDirection(DcMotorSimple.Direction.REVERSE);
//        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
//        slidePivot.setDirection(DcMotorSimple.Direction.REVERSE);
//
//
//        leftSlide.setTargetPosition(slidePos);
//        rightSlide.setTargetPosition(slidePos);
//        slidePivot.setTargetPosition(pivotPos);
//
//        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slidePivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        waitForStart();

//        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        slidePivot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (isStopRequested())
            return;

        while (opModeIsActive()) {
            runDrive();
            if(gamepad1.left_bumper) {
                servoIntakeRight.setPower(1);
                servoIntakeLeft.setPower(-1);
            } else {
                servoIntakeRight.setPower(0);
                servoIntakeLeft.setPower(0);
            }
            if(gamepad1.right_bumper) {
                servoIntakeRight.setPower(-1);
                servoIntakeLeft.setPower(1);
            } else {
                servoIntakeRight.setPower(0);
                servoIntakeLeft.setPower(0);
            }
            if(gamepad1.b) {
                servoHookLeft.setPosition(0);
                servoHookRight.setPosition(0.97);
            }
            if(gamepad1.a) {
                servoHookLeft.setPosition(0.9);
                servoHookRight.setPosition(0.09);
            }
            if(gamepad2.b) {
                servoSlideLeft.setPosition(0.6);
                servoSlideRight.setPosition(0.4);
            }
            if(gamepad2.a) {
                servoSlideLeft.setPosition(0.25);
                servoSlideRight.setPosition(0.75);
            }



            telemetry.addData("servoSlideLeft", servoSlideLeft.getPosition());
            telemetry.addData("servoSlideRight", servoSlideRight.getPosition());
            telemetry.addData("servoWristLeft", servoWristLeft.getPosition());
            telemetry.addData("servoWristRight", servoWristRight.getPosition());
            telemetry.addData("servoHookLeft", servoHookLeft.getPosition());
            telemetry.addData("servoHookRight", servoHookLeft.getPosition());
            telemetry.addData("motorWench", motorWench.getCurrentPosition());
            telemetry.update();




        }
    }

    public void runDrive() {
        DcMotor driveFrontleft = hardwareMap.dcMotor.get("driveFrontleft");
        DcMotor driveFrontright = hardwareMap.dcMotor.get("driveFrontright");
        DcMotor driveBackleft = hardwareMap.dcMotor.get("driveBackleft");
        DcMotor driveBackright = hardwareMap.dcMotor.get("driveBackright");

        double y = -gamepad1.left_stick_y; // remember this is reversed
        double x = gamepad1.left_stick_x * 1.1; // counteract imperfect strafing
        double rx = gamepad1.right_stick_x;
        double denominator = Math.max(Math.abs(y) + Math.abs(rx), 1);

        double frontleftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        driveFrontleft.setPower(-frontleftPower);
        driveBackleft.setPower(-backLeftPower);
        driveFrontright.setPower(-frontRightPower);
        driveBackright.setPower(-backRightPower);
    }


}


