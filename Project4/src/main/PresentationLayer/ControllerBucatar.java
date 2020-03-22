package PresentationLayer;

import BusinessLayer.Restaurant;

public class ControllerBucatar {
	ViewBucatar viewBucatar;
     Restaurant rest;
	BucatarObserver obsB;
	
	public ControllerBucatar(ViewBucatar viewBucatar,Restaurant rest) {
		super();
		this.viewBucatar=viewBucatar;
		this.rest=rest;
		obsB=new BucatarObserver(rest,viewBucatar);
		
		
	}

}
