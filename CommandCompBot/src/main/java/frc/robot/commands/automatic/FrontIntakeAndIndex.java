// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.automatic;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.PitchConstants;
import frc.robot.Constants.TurretConstants;
import frc.robot.Constants.YawConstants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;
import frc.robot.subsystems.turret.TurretYawSubsystem;

public class FrontIntakeAndIndex extends Command {
  /** Creates a new BackIntakeAndIndex. */

  private IntakeSubsystem intakeSubsystem;
  private IndexerSubsystem indexerSubsystem;
  private TurretYawSubsystem turretYawSubsystem;
  private ShooterPitchSubsystem shooterPitchSubsystem;
  private DigitalOutput indexerLimit;

  public FrontIntakeAndIndex(IntakeSubsystem intake, IndexerSubsystem indexer, TurretYawSubsystem turretYaw, ShooterPitchSubsystem shooterPitch, DigitalOutput limit) {
    this.intakeSubsystem = intake;
    this.indexerSubsystem = indexer;
    this.turretYawSubsystem = turretYaw;
    this.shooterPitchSubsystem = shooterPitch;

    addRequirements(intakeSubsystem,indexerSubsystem,turretYawSubsystem,shooterPitchSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turretYawSubsystem.setYaw(YawConstants.FRONT_INTAKE);
    shooterPitchSubsystem.setPitch(PitchConstants.PITCH_MIN);
    if (turretYawSubsystem.getTurretYaw() < (YawConstants.FRONT_INTAKE + 0.5) && turretYawSubsystem.getTurretYaw() > (YawConstants.FRONT_INTAKE - 0.5)) { 
      intakeSubsystem.runFrontIntake(IntakeConstants.INTAKE_SPEED);
    }

    if (indexerLimit.get() == false) {
      indexerSubsystem.runIndexer(TurretConstants.INDEXER_NORMAL_SPEED);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.stopFrontIntake();
    //indexerSubsystem.runIndexer(TurretConstants.INDEXER_BACK_SPEED);
    //new WaitCommand(TurretConstants.INDEXER_BACK_TIME);
    indexerSubsystem.stopIndexer();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
