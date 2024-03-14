package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class IO {
    
    public static final int DRIVE_JOYSTICK_PORT = 0;
    public static final int CONTROL_JOYSTICK_PORT = 1;

    public static final int LX_STICK_AXIS = 0;
    public static final int LY_STICK_AXIS = 1;
    public static final int RX_STICK_AXIS = 2;
    public static final int RY_STICK_AXIS = 3;
    public static final int NEW_RX_STICK_AXIS = 4;
    public static final int NEW_RY_STICK_AXIS = 5;

    public static final int X_STICK_AXIS = 0;
    public static final int Y_STICK_AXIS = 1;
    public static final int Z_ROTATE = 2;
    public static final int SLIDER = 3;

    public static final int L_TRIGGER_AXIS = 2;
    public static final int R_TRIGGER_AXIS = 3;

    public static final int PAD = 16;

    public static final int A_BUTTON = 2;
    public static final int B_BUTTON = 3;
    public static final int X_BUTTON = 1;
    public static final int Y_BUTTON = 4;
    public static final int LB_BUTTON = 5;
    public static final int RB_BUTTON = 6;
    public static final int BACK_BUTTON = 9;
    public static final int START_BUTTON = 10;
    public static final int L_STICK_BUTTON = 12;
    public static final int R_STICK_BUTTON = 13;
    public static final int L_TRIGGER_BUTTON = 7;
    public static final int R_TRIGGER_BUTTON = 8;

    public static final int STICK_TRIGGER = 1;

    public static Joystick driveJoystick;
    public static Joystick controlJoystick;  

    public IO() {
        driveJoystick = new Joystick(DRIVE_JOYSTICK_PORT);
        controlJoystick = new Joystick(CONTROL_JOYSTICK_PORT);
    }

    public static int buttonPressed(Joystick pJoystick) {
        
        if(pJoystick.getRawButton(A_BUTTON)) {
            return A_BUTTON;
        } else if(pJoystick.getRawButton(B_BUTTON)) {
            return B_BUTTON;
        } else if(pJoystick.getRawButton(X_BUTTON)) {
            return X_BUTTON;
        } else if(pJoystick.getRawButton(Y_BUTTON)) {
            return Y_BUTTON;
        } else if (pJoystick.getRawButton(LB_BUTTON)) {
            return LB_BUTTON;
        } else if (pJoystick.getRawButton(RB_BUTTON)) {
            return RB_BUTTON;
        } else if (pJoystick.getRawButton(BACK_BUTTON)) {
            return BACK_BUTTON;
        } else if (pJoystick.getRawButton(START_BUTTON)) {
            return START_BUTTON;
        } else if (pJoystick.getRawButton(L_STICK_BUTTON)) {
            return L_STICK_BUTTON;
        } else if (pJoystick.getRawButton(R_STICK_BUTTON)) {
            return R_STICK_BUTTON;
        } else if (pJoystick.getRawAxis(L_TRIGGER_AXIS) > 0.1f) {
        return L_TRIGGER_BUTTON;
        } else if(pJoystick.getRawAxis(R_TRIGGER_AXIS) > 0.1f) {
        return R_TRIGGER_BUTTON;
        

        } else if(pJoystick.getPOV() > -1){
            return PAD;
        } else if (pJoystick.getRawButton(STICK_TRIGGER)) {
            return STICK_TRIGGER;
    
        } else {
            return 0;
        } 
    } 

    public static void driveButtonsPressedJoystick() {//DRIVE CONTROLLER
        switch (buttonPressed(IO.driveJoystick)) {
            case STICK_TRIGGER:
                Drivetrain.sprintMode(true);
                break;
            case L_TRIGGER_BUTTON:
                System.out.println("left");
                // Drivetrain.leftSpin();
                break;
            case R_TRIGGER_BUTTON:
                System.out.println("right");
                // Drivetrain.rightSpin();
                
            default:
                Drivetrain.sprintMode(false);
            break;
        }
    }

    public static void controlButtonsPressed() {//DRIVE CONTROLLER
        switch (buttonPressed(IO.controlJoystick)) {
            case Y_BUTTON:
                
                Turret.driveShooterPitch(1);
                break;
            case B_BUTTON:
                Turret.shoot(1,1);
                
                break;
            case X_BUTTON:
                Turret.shooterPitchStop();
                Turret.shooterStop();
                Turret.indexerStop();
                break;
            case RB_BUTTON:
               // Manipulator.backIntakeIn();
                Turret.indexIn();
                Turret.shoot(1.0, 1.0);
                break;
            case LB_BUTTON:
                Manipulator.frontIntakeIn();
                break;
            case A_BUTTON:
                Turret.driveShooterPitch(-0.6);
               // Manipulator.indexOut();
                break;
            // case RB_BUTTON:
            //     if (Robot.frontIntakeSwitch.get()) {
            //         Manipulator.intakeIn();
            //     } else {
            //         Manipulator.intakeStop();
            //     }
            //     break;
            case START_BUTTON:
                Turret.rotateTurret(0.3);
                System.out.println("POSITIVE");
                break;
            case BACK_BUTTON:
            Turret.rotateTurret(-0.3);
            System.out.println("NEGATIVE");
                break;
            default:
                Manipulator.intakeStop();
                Turret.shooterPitchStop();
                Turret.stopTurret();
                
                // Manipulator.automaticIntake(false);
            break;
        }
    }
}
