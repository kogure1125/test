//package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.ActionListener;
import com.jme3.renderer.RenderManager;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import projectkyoto.jme3.mmd.CartoonEdgeProcessor;
import projectkyoto.jme3.mmd.PMDNode;
import projectkyoto.jme3.mmd.UpdateControl;
import projectkyoto.jme3.mmd.vmd.VMDControl;
import projectkyoto.mmd.file.VMDFile;
import com.jme3.scene.Node;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;


/**
 * test
 * <p/>
 * @author kobayasi
 */
public class Main extends SimpleApplication implements AnimEventListener {

    PointLight pl;
    Geometry lightMdl;
    VMDControl vmdControl;
    VMDControl vmdControl2;
    private AnimChannel channel;
    private AnimControl control;
    Node player;

    public static void main(String[] args){
//        com.jme3.system.JmeSystem.setLowPermissions(true);
        Main app = new Main();
        app.start();
        }
           

@Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(50);
        // Load Model
        PMDNode pmdNode = (PMDNode) assetManager.loadModel("Model/初音ミク.pmd");
        VMDFile vmd = (VMDFile) assetManager.loadAsset("motion/歩行モーション_おんな_ゆっくり.vmd");
        VMDFile vmd2 = (VMDFile) assetManager.loadAsset("motion/歩行モーション_おんな_ゆっくり.vmd");
        vmdControl = new VMDControl(pmdNode, vmd);
        vmdControl2= new VMDControl(pmdNode, vmd2);
        pmdNode.addControl(vmdControl);
        pmdNode.addControl(vmdControl2);
        pmdNode.addControl(new UpdateControl(pmdNode));
        vmdControl.setFrameNo(0);
        vmdControl2.setFrameNo(0);
        vmdControl.setPause(true);
        vmdControl2.setPause(true);

        rootNode.attachChild(pmdNode);
        control = pmdNode.getControl(AnimControl.class);
        control.addListener(this);


        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(1, 0, -5).normalizeLocal());
        dl.setColor(ColorRGBA.White.mult(0.5f));
        rootNode.addLight(dl);
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.0f));
        rootNode.addLight(al);
        CartoonEdgeProcessor cartoonEdgeProcess = new CartoonEdgeProcessor();
        viewPort.addProcessor(cartoonEdgeProcess);

        cam.setLocation(new Vector3f(0, 10, 40));
    }
    float time = 0;
    
    @Override
    public void simpleUpdate(float tpf) {
         time += tpf;
             if(time>0){       
             if(vmdControl != null) {
                 vmdControl2.setPause(true);
    }
}
}
public void initKeys(){
inputManager.addMapping("",new KeyTrigger(KeyInput.KEY_SPACE));
inputManager.addListener(actionListener,"motion/歩行モーション_おんな_ゆっくり.vmd");
}
public void onAnimChange(AnimControl control,AnimChannel channel,String animName){
}

public void onAnimCycleDone(AnimControl control,AnimChannel channel,String animName){
if(animName.equals("motion/歩行モーション_おんな_ゆっくり.vmd")){
   channel.setAnim("motion/歩行モーション_おんな_ゆっくり.vmd",0.50f);
   channel.setLoopMode(LoopMode.DontLoop);
   channel.setSpeed(1f);
    }
  }

private ActionListener actionListener = new ActionListener(){
  public void onAction(String name,boolean KeyPressed,float tpf){
    if(name.equals("motion/歩行モーション_おんな_ゆっくり.vmd") &&!KeyPressed){
      if(!channel.getAnimationName().equals("motion/歩行モーション_おんな_ゆっくり.vmd")){
         channel.setAnim("motion/歩行モーション_おんな_ゆっくり.vmd",0.50f);
         channel.setLoopMode(LoopMode.Loop);
    }
   }
  }
};
    }
