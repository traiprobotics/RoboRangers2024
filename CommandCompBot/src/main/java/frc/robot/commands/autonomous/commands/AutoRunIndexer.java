// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.turret.IndexerSubsystem;

public class AutoRunIndexer extends Command {
  /** Creates a new RunIndexer. */

  private double timestamp;
  private double runtime;

  private IndexerSubsystem indexerSubsystem;
  private double speed;

  public AutoRunIndexer(IndexerSubsystem indexer, double speed, double time) {
    this.indexerSubsystem = indexer;
    this.speed = speed;
    this.runtime = time;
    addRequirements(indexerSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timestamp = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (TurretConstants.canIndex) {
      indexerSubsystem.runIndexer(speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexerSubsystem.stopIndexer();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Timer.getFPGATimestamp() > timestamp + runtime) {
      return true;
    } else {
      return false;
    }
  }
}
