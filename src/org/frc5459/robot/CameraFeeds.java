package org.frc5459.robot;



import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.*;

public class CameraFeeds{
	private CameraServer cameraServer;
	private state state;
	public enum state{
		Front,
		Back,
	}
	
	public CameraFeeds(VideoSource front, VideoSource back) {
		this.cameraServer = CameraServer.getInstance();
		this.cameraServer.addCamera(front);
		this.cameraServer.addCamera(back);
		this.cameraServer.startAutomaticCapture(0);
		this.state = state.Front;
	}
	
	
	public void SwitchCamera(){
		
		if (state == state.Front) {
			cameraServer.putVideo("abck", 640, 480);
			state = state.Back;
		}else{
			cameraServer.putVideo("front", 640, 480);
			state = state.Front;
		}
		
		
		
		
	}
}
