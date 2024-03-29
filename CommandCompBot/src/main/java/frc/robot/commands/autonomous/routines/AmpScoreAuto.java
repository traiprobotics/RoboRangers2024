// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.PitchConstants;
import frc.robot.Constants.TurretConstants;
import frc.robot.Constants.YawConstants;
import frc.robot.commands.autonomous.commands.StartShooter;
import frc.robot.commands.autonomous.commands.StopDrive;
import frc.robot.commands.turret.RunIndexer;
import frc.robot.commands.turret.RunShooter;
import frc.robot.commands.turret.SetShooterPitchPreset;
import frc.robot.commands.turret.SetTurretYaw;
import frc.robot.commands.turret.SetTurretYawPreset;
import frc.robot.commands.autonomous.commands.StartShooter;
import frc.robot.commands.autonomous.commands.StartIndexer;
import frc.robot.commands.autonomous.commands.StartBackIntakeAndIndex;
import frc.robot.commands.autonomous.commands.StopEverything;
import frc.robot.commands.autonomous.commands.StartDrive;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;
import frc.robot.subsystems.turret.TurretYawSubsystem;

public class AmpScoreAuto extends Command {
  /** Creates a new ShootBackAndIntakeAuto. */

  private static ShooterSubsystem shooterSubsystem;
  private static IndexerSubsystem indexerSubsystem;
  private static DrivetrainSubsystem drivetrainSubsystem;
  private static IntakeSubsystem intakeSubsystem;
  private static TurretYawSubsystem turretYawSubsystem;
  private static ShooterPitchSubsystem shooterPitchSubsystem;

  public AmpScoreAuto(ShooterSubsystem shooter, IndexerSubsystem indexer, DrivetrainSubsystem drive, TurretYawSubsystem turretYaw, ShooterPitchSubsystem shooterPitch) {
    this.shooterSubsystem = shooter;
    this.indexerSubsystem = indexer;
    this.drivetrainSubsystem = drive;
    this.turretYawSubsystem = turretYaw;
    this.shooterPitchSubsystem = shooterPitch;
    addRequirements(shooterSubsystem, indexerSubsystem, drivetrainSubsystem, turretYawSubsystem, shooterPitchSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    new SequentialCommandGroup(
      new StartDrive(drivetrainSubsystem, 0.2, 0.2),
      new SetShooterPitchPreset(shooterPitchSubsystem, PitchConstants.AMP_SCORE_PITCH),
      new WaitCommand(2),
      new StopDrive(drivetrainSubsystem),
      new RunShooter(shooterSubsystem),
      new WaitCommand(1),
      new RunIndexer(indexerSubsystem, TurretConstants.INDEXER_SHOOT_SPEED),
      new WaitCommand(1),
      new StopEverything(intakeSubsystem, shooterSubsystem, indexerSubsystem, drivetrainSubsystem)

    );
   
    
    
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
