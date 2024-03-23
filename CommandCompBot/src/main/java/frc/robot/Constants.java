// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class CANConstants {
    public static final int FRONT_LEFT_DRIVE = 1;
    public static final int FRONT_RIGHT_DRIVE = 2;
    public static final int BACK_LEFT_DRIVE = 3;
    public static final int BACK_RIGHT_DRIVE = 4;

    public static final int BACK_INTAKE = 5;
    public static final int FRONT_INTAKE = 6;

    public static final int TURRET_YAW = 7;
    public static final int SHOOTER_PITCH = 8;

    public static final int INDEXER = 9;
    public static final int SHOOTER_LEFT = 10;
    public static final int SHOOTER_RIGHT = 11;

    public static final int LEFT_CLIMB = 12;
    public static final int RIGHT_CLIMB = 13;
  }

  public static class OperatorConstants {
    public static final int DRIVER_CONTROLLER = 0;
    public static final int DRIVER_JOYSTICK_MOVE_AXIS = 1;
    public static final int DRIVER_JOYSTICK_ROTATE_AXIS = 0;
    public static final int DRIVER_JOYSTICK_SLIDER = 3;
  }

  public static class DrivetrainConstants {
    public static final double RAMP_RATE = 0;
    public static final double DEFAULT_DRIVE_SPEED = 0.6;
    public static final double DEFAULT_TURN_SPEED = 0.6;
  }

  public static class IntakeConstants {
    public static final double INTAKE_SPEED = 1;
    public static final double OUTTAKE_SPEED = -1;
  }

  public static class TurretConstants {
    public static final double INDEXER_BACK_TIME = 0;
    public static final double INDEXER_BACK_SPEED = -0.3;
    public static final double SHOOTER_LEFT_SPEED = 1;
    public static final double SHOOTER_RIGHT_SPEED = 1;
    public static final double INDEXER_SHOOT_SPEED = 1;
    public static final double INDEXER_NORMAL_SPEED = 0.6;

  }

  public static class ClimbConstants {
    public static final double LEFT_SERVO_UNLOCK = 0.2;
    public static final double LEFT_SERVO_LOCK = 0.5;
    public static final double RIGHT_SERVO_UNLOCK = 0.7;
    public static final double RIGHT_SERVO_LOCK = 1;
    public static final double CLIMB_SPEED_DOWN = 1;
    public static final double CLIMB_SPEED_UP = 0.1;
  }

  public static class PIDConstants {

    public static final double YAW_POWER_MAX = 0.5;
    public static final double YAW_POWER_MIN = -0.5;
    public static final double YAW_P = 1.5;
    public static final double YAW_I = 0;
    public static final double YAW_D = 0;
    public static final double YAW_FF = 0;

    public static final double PITCH_P = 1.5;
    public static final double PITCH_I = 0;
    public static final double PITCH_D = 0;
    public static final double PITCH_FF = 0;
    public static final double PITCH_POWER_MIN = -0.1;
    //make the arm go slower when it lowers, as it is aided by gravity
    public static final double PITCH_POWER_MAX = 1;

  }

  public static class PitchConstants {
    public static final double PITCH_MIN = 0.42;
    public static final double PITCH_MAX = 0.7;
    public static final double AMP_SCORE_PITCH = 0.68;
  }

  public static class YawConstants {
    public static final double BACK_INTAKE = 0;
    public static final double FRONT_INTAKE = 18;

    public static final double YAW_HOME = 0;
    public static final double YAW_MAX = 36;
    public static final double YAW_MIN = -36;
    public static final double YAW_CONTROL_REDUCTION = 20;
  }
}
