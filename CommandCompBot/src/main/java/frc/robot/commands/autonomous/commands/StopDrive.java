// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.commands.DriveArcade;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;

public class StopDrive extends Command {
  /** Creates a new RunIntake. */

  private DrivetrainSubsystem drivetrainSubsystem;

  double speed;

  public StopDrive(DrivetrainSubsystem drivetrain) {
    this.drivetrainSubsystem = drivetrain;
  
    addRequirements(drivetrainSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("stopping drivetrain");

    drivetrainSubsystem.arcadeDrive(0, 0);
    drivetrainSubsystem.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    //System.out.println("execute");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    //System.out.println("stop");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //System.out.println("isFinished");
    return true;
  }
}
