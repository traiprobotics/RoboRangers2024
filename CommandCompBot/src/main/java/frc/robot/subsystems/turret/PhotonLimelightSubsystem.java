// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PhotonLimelightSubsystem extends SubsystemBase {
  /** Creates a new PhotonLimelightSubsystem. */

  
  PhotonCamera limelight = new PhotonCamera("Limelight");
  

  public PhotonLimelightSubsystem() {
  
  }

  public double getTagYaw(int id) {
    var result = limelight.getLatestResult();
    List<PhotonTrackedTarget> targets = result.getTargets();
    PhotonTrackedTarget desiredTarget = null;
    if (result.hasTargets()) {
      for (PhotonTrackedTarget target : targets) {
        if (target.getFiducialId() == id) {
          desiredTarget = target;
          System.out.println("Locked on to ID " + id);
        }
      }
      if (desiredTarget != null) {
        return desiredTarget.getYaw();
      } else {
        return 0;
      }
    } else {
      return 0;
    }
  }

    public double getTagPitch(int id) {
    var result = limelight.getLatestResult();
    List<PhotonTrackedTarget> targets = result.getTargets();
    PhotonTrackedTarget desiredTarget = null;
    if (result.hasTargets()) {
      for (PhotonTrackedTarget target : targets) {
        if (target.getFiducialId() == id) {
          desiredTarget = target;
        }
      }
      if (desiredTarget != null) {
        return desiredTarget.getPitch();
      } else {
        return 0;
      }
    } else {
      return 0;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
