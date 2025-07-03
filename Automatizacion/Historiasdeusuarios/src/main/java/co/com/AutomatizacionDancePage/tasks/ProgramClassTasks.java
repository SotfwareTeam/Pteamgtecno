package co.com.AutomatizacionDancePage.tasks;

import co.com.AutomatizacionDancePage.models.ClassesData;
import co.com.AutomatizacionDancePage.utils.hooks.ClasesVariables;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import java.util.List;

import static co.com.AutomatizacionDancePage.userinterface.ClassesUserInterface.*;

public class ProgramClassTasks implements Task {
    private List<ClassesData> classes;
    public ProgramClassTasks(List<ClassesData> classes) {this.classes = classes;}

    public static ProgramClassTasks insertarDatos(List<ClassesData> classes){
        return Instrumented.instanceOf(ProgramClassTasks.class).withProperties(classes);
    }

    @Override

    public <T extends Actor> void performAs(T actor){
        actor.attemptsTo(
                Click.on(BTN_CALENDARIO),
                Click.on(BTN_CALENDARIOMODAL),
                RefreshPage.now(),
                Click.on(BOTON_MES_SIG),
                Click.on(BOTON_FECHA1),
                Click.on(BOTON_PROGRAMAR),
                Click.on(INPUT_PROFESOR),
                Enter.theValue(classes.get(0).getProfesor()).into(INPUT_PROFESOR),
                Click.on(INPUT_HORA),
                Enter.theValue(classes.get(0).getHora()).into(INPUT_HORA),
                Click.on(INPUT_CLASE),
                Enter.theValue(classes.get(0).getClase()).into(INPUT_CLASE),
                Click.on(INPUT_DIFICULTAD),
                Click.on(SELECT_PRINCIPIANTE),
                Click.on(BOTON_GUARDAR)
                //-----------------------------------
                //Click.on(BOTON_FECHA1),
                //Click.on(BOTON_INFORMACION)
        );

        //theActorInTheSpotlight().remember();
        actor.remember(ClasesVariables.profesor.toString(), classes.get(0).getProfesor());
        actor.remember(ClasesVariables.clase.toString(), classes.get(0).getClase());
    }
}
