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
  public static class OperatorConstants {
    public static final int DRIVER_CONTROLLER = 0;
    public static final int DRIVER_JOYSTICK_MOVE_AXIS = 1;
    public static final int DRIVER_JOYSTICK_ROTATE_AXIS = 0;
    public static final int DRIVER_JOYSTICK_SLIDER = 3;
  }

  public static class DrivetrainConstants {
    public static final double RAMP_RATE = 0;
    public static double DEFAULT_DRIVE_SPEED = 0.6;
    public static double DEFAULT_TURN_SPEED = 0.6;
  }

  public static class IntakeConstants {
    public static double INTAKE_SPEED = 1;
  }

  public static class TurretConstants {
    public static double INDEXER_BACK_SPEED = -0.3;
    public static double SHOOTER_LEFT_SPEED = 1;
    public static double SHOOTER_RIGHT_SPEED = 1;
    public static double INDEXER_SHOOT_SPEED = 1;
    public static double INDEXER_NORMAL_SPEED = 0.6;
  }

  public static class ClimbConstants {
    public static final double LEFT_SERVO_UNLOCK = 0;
    public static final double LEFT_SERVO_LOCK = 0;
    public static final double RIGHT_SERVO_UNLOCK = 0;
    public static final double RIGHT_SERVO_LOCK = 0;
    public static final double CLIMB_SPEED_DOWN = 1;
    public static final double CLIMB_SPEED_UP = 0.1;
  }

  public static class PIDConstants {

    public static final double YAW_POWER_MAX = 0;
    public static final double YAW_POWER_MIN = 0;
    public static final double YAW_FF = 0;
    public static final double YAW_D = 0;
    public static final double YAW_I = 0;
    public static final double YAW_P = 0;


    public static final double PITCH_P = 1;
    public static final double PITCH_I = 0;
    public static final double PITCH_D = 0;
    public static final double PITCH_FF = 0;
    public static final double PITCH_POWER_MIN = -0.5;
    //make the arm go slower when it lowers, as it is aided by gravity
    public static final double PITCH_POWER_MAX = 1;

  }

  public static class PitchConstants {
    public static final double PITCH_MIN = 0.4;
    public static final double PITCH_MAX = 0.7;
    public static final double AMP_SCORE_PITCH = 0.65;
  }
}
