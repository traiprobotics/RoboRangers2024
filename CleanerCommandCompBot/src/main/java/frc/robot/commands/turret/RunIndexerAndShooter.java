// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;

public class RunIndexerAndShooter extends Command {
  /** Creates a new RunIndexer. */

  private IndexerSubsystem indexerSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private double indexSpeed;
  private double shootSpeed;

  public RunIndexerAndShooter(IndexerSubsystem indexer, ShooterSubsystem shooter, double indexSpeed, double shootSpeed) {
    this.indexerSubsystem = indexer;
    this.shooterSubsystem = shooter;
    this.indexSpeed = indexSpeed;
    this.shootSpeed = shootSpeed;
    addRequirements(indexerSubsystem, shooterSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (TurretConstants.canIndex) {
      indexerSubsystem.runIndexer(indexSpeed);
      //shooterSubsystem.shoot(shootSpeed, shootSpeed);
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
    return false;
  }
}
