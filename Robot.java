/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import jdk.jfr.Percentage;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  public static VictorSPX spinMotor = new VictorSPX(1);;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  
  
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  private final Joystick joy = new Joystick(0);
  

  @Override
  public void robotInit() {
  
  

  m_colorMatcher.addColorMatch(kBlueTarget);
  m_colorMatcher.addColorMatch(kGreenTarget);
  m_colorMatcher.addColorMatch(kRedTarget);
  m_colorMatcher.addColorMatch(kYellowTarget);  
       
  }

@Override
    public void robotPeriodic() {

    
}
       

    
     ;
  
      

 
  @Override
  public void autonomousInit() {
  

  

  
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {

    
    Color detectedColor = m_colorSensor.getColor();
   
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);   

    if (match.color == kBlueTarget){
      colorString = "Blue";
    } else if (match.color == kRedTarget){
      colorString = "Red";
    } else if (match.color == kGreenTarget){
      colorString = "Green";
    } else if (match.color == kYellowTarget){
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    
    
  if (joy.getRawButton(1)) {   
    spinMotor.set(ControlMode.PercentOutput, 0.5);
    while (match.color == kGreenTarget){
      spinMotor.set(ControlMode.PercentOutput, 0);
    }
  } else if (joy.getRawButton(2)){
      spinMotor.set(ControlMode.PercentOutput, 0.5);
      while (match.color == kRedTarget){
        spinMotor.set(ControlMode.PercentOutput, 0);   
      }
  } else if (joy.getRawButton(3)){
    spinMotor.set(ControlMode.PercentOutput, 0.5);
    while (match.color == kBlueTarget){
      spinMotor.set(ControlMode.PercentOutput, 0);
    }
  } else if (joy.getRawButton(4)){
    spinMotor.set(ControlMode.PercentOutput, 0.5);
    while (match.color == kYellowTarget){
      spinMotor.set(ControlMode.PercentOutput, 0);
    }
  }  


    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Deceted Color", colorString);
}


  
  
  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
