// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto.routines;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.TurretConstants;
import frc.robot.commands.turret.RunIndexer;
import frc.robot.commands.turret.RunShooter;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;

public class ShootBackAndIntakeAuto extends Command {
  /** Creates a new ShootBackAndIntakeAuto. */

  private static ShooterSubsystem shooterSubsystem;
  private static IndexerSubsystem indexerSubsystem;
  private static DrivetrainSubsystem drivetrainSubsystem;
  private static IntakeSubsystem intakeSubsystem;

  public ShootBackAndIntakeAuto(ShooterSubsystem shooter, IndexerSubsystem indexer, DrivetrainSubsystem drive, IntakeSubsystem intake) {
    this.shooterSubsystem = shooter;
    this.indexerSubsystem = indexer;
    this.drivetrainSubsystem = drive;
    this.intakeSubsystem = intake;
    addRequirements(shooterSubsystem, indexerSubsystem, drivetrainSubsystem, intakeSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
