package game.entities;

import game.Game;
import game.entities.enemies.Enemy;
import game.entities.projectiles.Projectile;
import util.KeyManagerListener;
import util.MarioDiesException;
import util.MarioKeyListener;
import util.Side;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.awt.Image;
import java.awt.Toolkit;

import core.CollisionDetector;

public class Mario extends MovableEntity implements KeyManagerListener {
    public static final int VEL_X_CAP = 5;
    public static final int VEL_Y_CAP = 20;
    private static int deathCounter = 0;
    private int score = 0;
    private boolean crouch = false;
    public static Image dyingMario = Toolkit.getDefaultToolkit()
            .createImage("Resources//Images//Pandas//Death-Animation.gif");
    public static final Image rRunningMario = Toolkit.getDefaultToolkit()
            .createImage("Resources//Images//Pandas//Right-Running-Panda.gif");
    public static final Image lRunningMario = Toolkit.getDefaultToolkit()
            .createImage("Resources//Images//Pandas//Right-Running-Panda.gif");
    public static final Image rJumpingMario = Toolkit.getDefaultToolkit()
            .createImage("Resources//Images//Pandas//RJumpPanda.png");
    public static final Image lJumpingMario = Toolkit.getDefaultToolkit()
            .createImage("Resources//Images//Pandas//LJumpPanda.png");
    public static final Image standingMario = Toolkit.getDefaultToolkit()
            .createImage("Resources//Images//Pandas//Panda.gif");

    private boolean isJumping = false;

    Vector<MarioKeyListener> jumpListeners = new Vector<MarioKeyListener>(0, 1);

    public Mario(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        Game.getKeyManager().listenFor(KeyEvent.VK_UP, this);
    }

    /**
     * Method that updates mario every frame
     * 
     * @throws MarioDiesException
     */
    @Override
    public void update() throws MarioDiesException {
        accelX = crouch ? 0 : Game.getKeyManager().getHorizontalDir() / 5.0;

        if (velX == 0 && accelX == 0) {
            setImage(standingMario);
        } else if (isJumping && accelX != 0) {
            setImage(accelX < 0 ? lJumpingMario : rJumpingMario);

        } else if (accelX != 0) {
            setImage(accelX < 0 ? lRunningMario : rRunningMario);
        }

        if (offLeftScreen((int) (Math.ceil(x + accelX)))) {
            velX = 0;
            x = Game.getGameState().getCamera().getmaxX() - (Game.getGameState().getCamera().getwidth() / 2);
            accelX = 0;
        }
        if (y > 1850) {
            throw new MarioDiesException();
        }
        // accelY = Game.getKeyManager().getVerticalDir();
        if (Game.getKeyManager().getHorizontalDir() == 0) {
            velX = 0;
        }

        if (crouch && !Game.getKeyManager().down) {
            crouch = false;
            move(0, -10);
            height = 45;
        } else if (!crouch && Game.getKeyManager().down) {
            crouch = true;
            height = 35;
            move(0, 10);

        }

        assureVelIsCapped();

        super.update();

        isJumping = accelY > 0 ? true : false;
        // System.out.println("Velocities: :"+velX+" "+velY);

        checkCollisions();

    }

    private boolean offLeftScreen(int nextX) {
        return nextX <= Game.getGameState().getCamera().getmaxX() - (Game.getGameState().getCamera().getwidth() / 2);
    }

    /**
     * Method that caps mario's speed horizontally and vertically
     */
    private void assureVelIsCapped() {
        velX = Math.abs(velX) >= VEL_X_CAP ? VEL_X_CAP * velX / Math.abs(velX) : velX;
        velY = Math.abs(velY) >= VEL_Y_CAP ? VEL_Y_CAP * velY / Math.abs(velY) : velY;

    }

    @Override
    public void draw(Graphics g, int xOffset, int yOffset) {
        g.drawImage(image, x - xOffset, y - yOffset, null);
        // g.drawImage(brick.image, brick.getX(), brick.getY(),null);
    }

    @Override
    public void hitSide(Entity e, Side side) {
        System.out.println(e.getClass());
        if (e.getSolid()) {
            if (side.getSide() == Side.TOP || side.getSide() == Side.BOTTOM) {
                setImage(standingMario);
                if (side.getSide() == Side.BOTTOM) {
                    isJumping = false;
                }
                System.out.println(e instanceof Projectile);
                if (side.getSide() == Side.BOTTOM && (e instanceof Enemy || e instanceof Projectile)) {
                    velY = -15;
                    accelY = 0.7;
                    System.out.println("jumping");
                } else {

                    accelY = 0;
                    velY = 0;
                }

            } else if (side.getSide() == Side.LEFT || side.getSide() == Side.RIGHT) {
                accelX = 0;
                velX = 0;
            }
        }
    }

    /**
     * Checks for and resolves collisions
     * 
     * @return True if mario has hit something (solid or not), false otherwise
     * @throws MarioDiesException
     */
    // private boolean checkCollisions() {
    // System.out.println(velY);
    // Vector<Entity> entities = CollisionDetector.getBlockCollisions(this);
    // Vector<Enemy> enemies = CollisionDetector.getEnemyCollisions(this);
    // if (entities.size() != 0 || enemies.size() != 0) {
    // for (Entity e : entities) {
    // CollisionDetector.resolveCollision(this, (int) Math.round(velX), (int)
    // Math.round(velY), e);
    // }
    // for (Enemy e : enemies) {
    // CollisionDetector.resolveCollision(this, (int) Math.round(velX), (int)
    // Math.round(velY), e);
    // }
    // return true;
    // } else {
    // return false;
    // }

    // }

    private boolean checkCollisions() throws MarioDiesException {

        Vector<Entity> entity = CollisionDetector.getBlockCollisions(this);
        if (entity.size() == 0) {

            for (Projectile p : Game.getGameState().projectiles) {
                if (p.getArea().intersects(area)) {
                    CollisionDetector.resolveCollision(this, (int) Math.round(velX), (int) Math.round(velY), p);
                }
            }
            for (Enemy e : Game.getGameState().enemies) {
                if (e.getArea().intersects(area)) {
                    CollisionDetector.resolveCollision(this, (int) Math.round(velX), (int) Math.round(velY), e);

                }
            }
            return false;
        } else {
            double velX1 = velX;
            double velY1 = velY;
            for (Entity e : entity)
                CollisionDetector.resolveCollision(this, (int) Math.round(velX1), (int) Math.round(velY1), e);
            return false;
        }

    }

    /**
     * Method that adds to the player score
     * 
     * @param deltaScore Amount of points to be added
     */
    public void addToScore(int deltaScore) {
        score += deltaScore;
    }

    @Deprecated
    public void loseLife() {
        System.out.println("Ouch");
        Game.getGameState().reset();

    }

    @Override
    public void notify(KeyEvent e) {
        if (!isJumping) {
            velY = -15;
            accelY = 0.7;
            notifyJumpListeners();
        }
    }

    public void listenForJump(MarioKeyListener m) {
        jumpListeners.add(m);
    }

    private void notifyJumpListeners() {
        for (MarioKeyListener m : jumpListeners) {
            m.notifyJump();
        }
    }

    public void setImage(Image imageFile) {

        image = imageFile;
    }

    public static int getDeaths() {
        return deathCounter;
    }

    public static void addDeath() {
        deathCounter++;
    }

    public int getScore() {
        return score;
    }

}