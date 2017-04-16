package main.java.com.hotel.metier;

import com.jfoenix.controls.JFXListView;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;

@FXMLController(value = "/main/java/com/hotel/presentation/SideMenu.fxml", title = "")
public class SideMenu {

	@FXMLViewFlowContext
	private ViewFlowContext context;

	@FXML
	@ActionTrigger("buttons")
	private Label button;


	@FXML
	private JFXListView<Label> sideList;

	@PostConstruct
	public void init() throws FlowException, VetoException {
		FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");
		sideList.propagateMouseEventsToParent();
		sideList.getSelectionModel().selectedItemProperty().addListener((o,oldVal,newVal)->{
			if(newVal!=null){
				try {
					contentFlowHandler.handle(newVal.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});
		Flow contentFlow = (Flow) context.getRegisteredObject("ContentFlow");
		bindNodeToController(button, Home.class, contentFlow, contentFlowHandler);
//		bindNodeToController(checkbox, CheckboxController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(combobox, ComboBoxController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(dialogs, DialogController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(icons, IconsController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(listview, ListViewController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(treetableview, TreeTableViewController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(progressbar, ProgressBarController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(radiobutton, RadioButtonController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(slider, SliderController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(spinner, SpinnerController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(textfield, TextFieldController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(togglebutton, ToggleButtonController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(popup, PopupController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(svgLoader, SVGLoaderController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(pickers, PickersController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(masonry, MasonryPaneController.class, contentFlow, contentFlowHandler);
//		bindNodeToController(scrollpane, ScrollPaneController.class, contentFlow, contentFlowHandler);
	}

	private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
		flow.withGlobalLink(node.getId(), controllerClass);
	}

}
