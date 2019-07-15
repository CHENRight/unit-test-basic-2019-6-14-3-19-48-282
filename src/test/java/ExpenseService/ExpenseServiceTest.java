package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ExpenseService.ExpenseService.getExpenseCodeByProjectTypeAndName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {

        Project project = new Project(ProjectType.INTERNAL,"name");

        ExpenseType result = getExpenseCodeByProjectTypeAndName(project);

        Assertions.assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE,result);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL,"Project A");

        ExpenseType result = getExpenseCodeByProjectTypeAndName(project);

        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_A,result);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL,"Project B");

        ExpenseType result = getExpenseCodeByProjectTypeAndName(project);

        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_B,result);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL,"Other");

        ExpenseType result = getExpenseCodeByProjectTypeAndName(project);

        Assertions.assertEquals(ExpenseType.OTHER_EXPENSE,result);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE,"name");

        Assertions.assertThrows(UnexpectedProjectTypeException.class,() -> getExpenseCodeByProjectTypeAndName(project));
    }
}