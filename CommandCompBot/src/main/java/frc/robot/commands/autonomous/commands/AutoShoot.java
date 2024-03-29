// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.TurretConstants;
import frc.robot.commands.automatic.CameraTurretYaw;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.PhotonLimelightSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;
import frc.robot.subsystems.turret.TurretYawSubsystem;

public class AutoShoot extends Command {
  /** Creates a new AutoShoot. */
  TurretYawSubsystem turretYawSubsystem;
  //PhotonLimelightSubsystem photonLimelightSubsystem;
  ShooterSubsystem shooterSubsystem;
  IndexerSubsystem indexerSubsystem;
  //int id;

  double timestamp;

  public AutoShoot(ShooterSubsystem shooter, IndexerSubsystem indexer) {
    //this.turretYawSubsystem = turret;
    //this.photonLimelightSubsystem = limelight;
    this.shooterSubsystem = shooter;
    this.indexerSubsystem = indexer;
    //this.id = id;
    addRequirements(shooterSubsystem, indexerSubsystem);
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
    //new CameraTurretYaw(turretYawSubsystem, photonLimelightSubsystem, id);
    shooterSubsystem.shoot(TurretConstants.SHOOTER_TOP_SPEED, TurretConstants.SHOOTER_BOTTOM_SPEED);
    if (shooterSubsystem.getTopVelocity() > TurretConstants.SHOOTER_TOP_SPEED - 500) {
      indexerSubsystem.runIndexer(TurretConstants.INDEXER_SHOOT_SPEED);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Timer.getFPGATimestamp() > timestamp + 4) {
      shooterSubsystem.stopShooter();
      indexerSubsystem.stopIndexer();
      return true;
    } else {
      return false;
    }
  }
}
