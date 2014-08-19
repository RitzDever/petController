import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PetController {
	public static boolean givePetToPlayer(Player p, String pet) {
		try {
			Class<?> petTypeClass = Bukkit.getPluginManager()
					.getPlugin("Pet").getClass().getClassLoader()
					.loadClass("io.github.dsh105.pet.entity.PetType");
			Method enumGrabber = petTypeClass
					.getMethod("valueOf", String.class);
			Object type = enumGrabber.invoke(null, pet);

			Class<?> petAPI = Bukkit.getPluginManager().getPlugin("Pet")
					.getClass().getClassLoader()
					.loadClass("io.github.dsh105.pet.api.PetAPI");
			Method instanceGrabber = petAPI.getMethod("getAPI");
			Method petGiver = petAPI.getMethod("givePet", Player.class,
					petTypeClass, boolean.class);

			Object instance = instanceGrabber.invoke(null);
			Object result = petGiver.invoke(instance, p, type, true);
			return result != null;
		} catch (Exception e) {
		}
		return false;
	}
}