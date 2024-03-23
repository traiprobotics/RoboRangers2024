// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto.routines;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.turret.ShooterSubsystem;

public class ShootAndBackAuto extends Command {
  /** Creates a new ShootAndBackAuto. */

  private static ShooterSubsystem shooterSubsystem;
  private static DrivetrainSubsystem drivetrainSubsystem;

  public ShootAndBackAuto(ShooterSubsystem shooter, DrivetrainSubsystem drive) {
    this.shooterSubsystem = shooter;
    this.drivetrainSubsystem = drive;
    addRequirements(shooterSubsystem, drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
