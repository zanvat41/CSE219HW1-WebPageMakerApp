package wpm;

import java.util.Locale;
import static javafx.application.Application.launch;
import properties_manager.PropertiesManager;
import saf.components.AppComponentsBuilder;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;
import saf.AppTemplate;
import saf.components.AppWorkspaceComponent;
import saf.ui.AppMessageDialogSingleton;
import static wpm.PropertyType.UPDATE_ERROR_MESSAGE;
import static wpm.PropertyType.UPDATE_ERROR_TITLE;
import wpm.data.DataManager;
import wpm.file.FileManager;
import wpm.gui.Workspace;

/**
 * This class serves as the application class for our Web Page Maker program. 
 * Note that much of its behavior is inherited from AppTemplate, as defined in
 * the Simple App Framework. This app starts by loading all the app-specific
 * messages like icon files and tooltips and other settings, then the full 
 * User Interface is loaded using those settings. Note that this is a 
 * JavaFX application.
 * 
 * @author Richard McKenna
 * @author Zhe Lin
 * @version 1.0
 */
public class WebPageMaker extends AppTemplate {
    /**
     * This builder provides methods for properly setting up all
     * the custom objects needed to run this application. Note that
     * by swapping out these components we could have a very different
     * program that did something completely different.
     * 
     * @return The builder object that knows how to create the proper
     * components for this custom application.
     */
    @Override
    public AppComponentsBuilder makeAppBuilderHook() {
	return new AppComponentsBuilder() {
	    /**
	     * Makes the returns the data component for the app.
	     * 
	     * @return The component that will manage all data
	     * updating for this application.
	     * 
	     * @throws Exception An exception may be thrown should
	     * data updating fail, which can then be customly handled.
	     */
	    @Override
	    public AppDataComponent buildDataComponent() throws Exception {
                try{
                    return new DataManager(WebPageMaker.this);
                } catch(Exception e){
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    PropertiesManager props = PropertiesManager.getPropertiesManager();
                    dialog.show(props.getProperty(UPDATE_ERROR_TITLE), props.getProperty(UPDATE_ERROR_MESSAGE));
                    return null;
                }
	    }

	    /**
	     * Makes the returns the file component for the app.
	     * 
	     * @return The component that will manage all file I/O
	     * for this application.
	     * 
	     * @throws Exception An exception may be thrown should
	     * file I/O updating fail, which can then be customly handled.
	     */
	    @Override
	    public AppFileComponent buildFileComponent() throws Exception {
		try{
                    return new FileManager();
                } catch(Exception e){
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    PropertiesManager props = PropertiesManager.getPropertiesManager();
                    dialog.show(props.getProperty(UPDATE_ERROR_TITLE), props.getProperty(UPDATE_ERROR_MESSAGE));
                    return null;
                }
	    }

	    /**
	     * Makes the returns the workspace component for the app.
	     * 
	     * @return The component that serve as the workspace region of
	     * the User Interface, managing all controls therein.
	     * 
	     * @throws Exception An exception may be thrown should
	     * UI updating fail, which can then be customly handled.
	     */
	    @Override
	    public AppWorkspaceComponent buildWorkspaceComponent() throws Exception {
		try{
                    return new Workspace(WebPageMaker.this);
                } catch(Exception e){
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    PropertiesManager props = PropertiesManager.getPropertiesManager();
                    dialog.show(props.getProperty(UPDATE_ERROR_TITLE), props.getProperty(UPDATE_ERROR_MESSAGE));
                    return null;
                }
	    }
	};
    }
    
    /**
     * This is where program execution begins. Since this is a JavaFX app it
     * will simply call launch, which gets JavaFX rolling, resulting in sending
     * the properly initialized Stage (i.e. window) to the start method inherited
     * from AppTemplate, defined in the SimpleAppFramework.
     */
    public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	launch(args);
    }
}
