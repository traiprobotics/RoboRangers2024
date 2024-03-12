package frc.robot;

import javax.print.CancelablePrintJob;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Turret {
    private static CANSparkMax shooterPitch;
    private static CANSparkMax indexer;
    private static CANSparkMax shooterLeft;
    private static CANSparkMax shooterRight;

public Turret(){
    shooterPitch = new CANSparkMax(8,com.revrobotics.CANSparkLowLevel.MotorType.kBrushed);
    indexer = new CANSparkMax(9,com.revrobotics.CANSparkLowLevel.MotorType.kBrushed);
    shooterLeft = new CANSparkMax(10,com.revrobotics.CANSparkLowLevel.MotorType.kBrushed);
    shooterRight = new CANSparkMax(11,com.revrobotics.CANSparkLowLevel.MotorType.kBrushed);

}
    
}
