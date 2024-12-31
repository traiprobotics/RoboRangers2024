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
  double latestTagPitch = 18;

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
        latestTagPitch = desiredTarget.getPitch();
        return desiredTarget.getPitch();
      } else {
        return latestTagPitch;
      }
    } else {
      return latestTagPitch;
    }
    
  }

    public double getTagArea(int id) {
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
        return desiredTarget.getArea();
      } else {
        return 0;
      }
    } else {
      return 0;
    }
  }

  public double getTagRoughDistance(int id) {
    return 0;
    // var result = limelight.getLatestResult();
    // List<PhotonTrackedTarget> targets = result.getTargets();
    // PhotonTrackedTarget desiredTarget = null;
    // double previousArea = 100;
    // if (result.hasTargets()) {
    //   for (PhotonTrackedTarget target : targets) {
    //     if (target.getFiducialId() == id) {
    //       desiredTarget = target;
    //     }
    //   }
    //   if (desiredTarget != null) {
    //     double targetArea = desiredTarget.getArea();
    //     //convert area scaling to feet
    //     System.out.println(targetArea);
    //     System.out.println(targetArea);
    //     double inches = PitchConstants.MAX_RANGE - (targetArea - PitchConstants.MIN_TAG_AREA) * 
    //     ((PitchConstants.MAX_RANGE - PitchConstants.MIN_RANGE) / (PitchConstants.MAX_TAG_AREA - PitchConstants.MIN_TAG_AREA));
    //     System.out.println("Inches to target: " + inches);
    //     previousArea = targetArea;
    //     return inches;
    //   } else {
    //     return previousArea;
    //   }
    // } else {
    //   return previousArea;
    // }
// area next to shooter = 1
// area at max range 103" = 0.15
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
