/**
 * Created by jack on 2017-03-22.
 */
public class Queries {
    public static final String getEvents = "SELECT * FROM `discover`.`events`;";
    public static final String createTable = "CREATE TABLE IF NOT EXISTS `discover`.`events` ( `id` INT NOT NULL AUTO_INCREMENT, `event_id` VARCHAR(45) NULL, `name` VARCHAR(45) NULL, `duration` VARCHAR(45) NULL, `created_at` VARCHAR(45) NULL, `begins_at` VARCHAR(45) NULL, `ends_at` VARCHAR(45) NULL, `hosts` VARCHAR(256) NULL, `admins` VARCHAR(256) NULL, `attendees` VARCHAR(8000) NULL, PRIMARY KEY (`id`));";
}
