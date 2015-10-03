package br.edu.ifpb.db.myplaces.core;

/**
 *
 * @author joaomarcos
 */
public class FieldValidator {

    public static boolean isNotEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
