// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.turret.IndexerSubsystem;

public class RunIndexer extends Command {
  /** Creates a new RunIndexer. */

  private IndexerSubsystem indexerSubsystem;
  private double speed;

  public RunIndexer(IndexerSubsystem indexer, double speed) {
    this.indexerSubsystem = indexer;
    this.speed = speed;
    addRequirements(indexerSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    indexerSubsystem.runIndexer(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexerSubsystem.stopIndexer();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}