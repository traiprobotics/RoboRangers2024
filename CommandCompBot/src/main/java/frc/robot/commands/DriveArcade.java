// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.Constants.OperatorConstants;

public class DriveArcade extends Command {
  /** Creates a new DriveArcade. */
  public DriveArcade() {
    // Use addRequirements() here to declare subsystem dependencies.
    
    addRequirements(RobotContainer.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double moveSpeed = RobotContainer.driverJoystick.getRawAxis(OperatorConstants.DRIVER_JOYSTICK_MOVE_AXIS);
    double rotateSpeed = RobotContainer.driverJoystick.getRawAxis(OperatorConstants.DRIVER_JOYSTICK_ROTATE_AXIS);

    RobotContainer.drivetrain.arcadeDrive(moveSpeed, rotateSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}