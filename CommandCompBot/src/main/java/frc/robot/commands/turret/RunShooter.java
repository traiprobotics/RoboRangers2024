// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.turret.IndexerSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;

public class RunShooter extends Command {
  /** Creates a new RunShooter. */

  private ShooterSubsystem shooterSubsystem;

  public RunShooter(ShooterSubsystem shooter) {
    this.shooterSubsystem = shooter;
    addRequirements(shooterSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    TurretConstants.canIndex = false;
    shooterSubsystem.shoot(TurretConstants.SHOOTER_TOP_SPEED, TurretConstants.SHOOTER_BOTTOM_SPEED);
    if (shooterSubsystem.getTopVelocity() > TurretConstants.SHOOTER_TOP_SPEED - 500) {
      TurretConstants.canIndex = true;
    }
    System.out.println(shooterSubsystem.getBottomVelocity());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopShooter();
    TurretConstants.canIndex = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
