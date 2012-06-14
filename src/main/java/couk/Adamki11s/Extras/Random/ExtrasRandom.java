package couk.Adamki11s.Extras.Random;

import java.util.List;
import java.util.Random;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ExtrasRandom extends RandomMethods {

    private EntityType[] creatures = {EntityType.CHICKEN, EntityType.COW, EntityType.CREEPER, EntityType.GHAST, EntityType.GIANT,
        EntityType.PIG, EntityType.PIG_ZOMBIE, EntityType.SHEEP, EntityType.SKELETON,
        EntityType.SLIME, EntityType.SPIDER, EntityType.SQUID, EntityType.WOLF, EntityType.ZOMBIE};

    @Override
    public int getRandomInt(int upperBound, int lowerBound) {
        Random r = new Random();
        return r.nextInt(upperBound) + lowerBound;
    }

    @Override
    public double getRandomDouble(double upperBound, double lowerBound) {
        Random r = new Random();
        return (double) ((r.nextInt((int) upperBound) + (int) lowerBound) + r.nextDouble());
    }

    @Override
    public float getRandomFloat(float upperBound, float lowerBound) {
        Random r = new Random();
        return (float) ((r.nextInt((int) upperBound) + (int) lowerBound) + r.nextFloat());
    }

    @Override
    public EntityType getRandomCreature() {
        int random = getRandomInt(15, 1);
        return creatures[random];
    }

    @Override
    public Entity getRandomLivingEntityFromWorld(World w) {
        int entities = w.getEntities().size();
        List<Entity> ents = w.getEntities();
        Entity e = null;
        while (e == null || !(e instanceof LivingEntity)) {
            e = ents.get(getRandomInt(entities, 1));
        }
        return e;
    }

    @Override
    public Player getRandomPlayer(Server s) {
        return s.getOnlinePlayers()[getRandomInt(s.getOnlinePlayers().length - 1, 0)];
    }

    @Override
    public int getRandomBlockId() {
        int id = -1;
        while (!(id >= 0 && id <= 96) && !(id >= 256 && id <= 359) && !(id == 2256 || id == 2257)) {
            id = getRandomInt(2257, 0);
        }
        return id;
    }
}
