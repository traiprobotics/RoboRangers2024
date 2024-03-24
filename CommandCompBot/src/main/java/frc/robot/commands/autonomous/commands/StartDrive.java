// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class StartDrive extends Command {
  /** Creates a new DriveArcade. */

  private DrivetrainSubsystem drivetrainSubsystem;


  private double desiredTime;
  //DISTANCE IS IN INCHES
  private double desiredTurn;

  private double leftDrive;
  private double rightDrive;

  public StartDrive(DrivetrainSubsystem drive, double left, double right) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrainSubsystem = drive;
    this.leftDrive = left;
    this.rightDrive = right;
    addRequirements(drivetrainSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrainSubsystem.driveAuto(leftDrive, rightDrive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //RobotContainer.drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
