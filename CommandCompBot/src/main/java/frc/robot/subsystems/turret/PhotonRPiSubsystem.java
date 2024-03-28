// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PitchConstants;

public class PhotonRPiSubsystem extends SubsystemBase {
  /** Creates a new PhotonLimelightSubsystem. */

  
  PhotonCamera camera = new PhotonCamera("RPi Camera");
  

  public PhotonRPiSubsystem() {
  }

  public double getClosestNoteYaw() {
    var result = camera.getLatestResult();
    if (result.hasTargets()) {
      PhotonTrackedTarget bestNote = result.getBestTarget();
      return bestNote.getYaw();
    } else {
      return 0;
    }
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
