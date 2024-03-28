// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.automatic;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.turret.PhotonRPiSubsystem;

public class CameraNotePickup extends Command {
  /** Creates a new CameraNotePickup. */

  private DrivetrainSubsystem drivetrainSubsystem;
  private PhotonRPiSubsystem photonRPiSubsystem;

  public CameraNotePickup(DrivetrainSubsystem drive, PhotonRPiSubsystem camera) {
    this.drivetrainSubsystem = drive;
    this.photonRPiSubsystem = camera;
    addRequirements(drivetrainSubsystem, photonRPiSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (photonRPiSubsystem.getClosestNoteYaw() > 1) {
      //turn right
    } else if (photonRPiSubsystem.getClosestNoteYaw() < -1) {
      //turn left
    } else {
      //stop turning
    }
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
