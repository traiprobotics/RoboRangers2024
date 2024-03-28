// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.PitchConstants;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveArcade extends Command {
  /** Creates a new DriveArcade. */

  private DrivetrainSubsystem drivetrainSubsystem;
  private Joystick driveJoystick;

  double drive;
  double turn;

  public DriveArcade(DrivetrainSubsystem drive, Joystick joy) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrainSubsystem = drive;
    this.driveJoystick = joy;
    addRequirements(drivetrainSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive = 0;
    turn = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double slider = -((driveJoystick.getRawAxis(OperatorConstants.DRIVER_JOYSTICK_SLIDER) - 1) / 2);
    drive = driveJoystick.getRawAxis(OperatorConstants.DRIVER_JOYSTICK_MOVE_AXIS) * slider;
    turn = driveJoystick.getRawAxis(OperatorConstants.DRIVER_JOYSTICK_ROTATE_AXIS);

    drivetrainSubsystem.arcadeDrive(drive * DrivetrainConstants.DEFAULT_DRIVE_SPEED, turn);

    //System.out.println("Left: " + drivetrainSubsystem.getLeftEncoder());
    //System.out.println("Right: " + drivetrainSubsystem.getRightEncoder());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //RobotContainer.drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
