package eyeq.oden;

import eyeq.oden.block.BlockKonjac;
import eyeq.oden.block.BlockKonjacIce;
import eyeq.oden.block.BlockKonjacLava;
import eyeq.oden.block.BlockKonjacPoisonous;
import eyeq.oden.entity.projectile.EntityEggExplosive;
import eyeq.oden.item.*;
import eyeq.util.block.UBlockDrops;
import eyeq.util.client.model.UModelCreator;
import eyeq.util.client.model.UModelLoader;
import eyeq.util.client.model.gson.BlockmodelJsonFactory;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.common.registry.UEntityRegistry;
import eyeq.util.creativetab.UCreativeTab;
import eyeq.util.item.UItemFood;
import eyeq.util.oredict.CategoryTypes;
import eyeq.util.oredict.UOreDictionary;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.io.File;

import static eyeq.oden.Oden.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
@Mod.EventBusSubscriber
public class Oden {
    public static final String MOD_ID = "eyeq_oden";

    @Mod.Instance(MOD_ID)
    public static Oden instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    public static Item odenA1;
    public static final CreativeTabs TAB_ODEN = new UCreativeTab("Oden", () -> new ItemStack(odenA1));

    public static Block blockKonjac;
    public static Block blockKonjacPoisonous;
    public static Block blockKonjacLava;
    public static Block blockKonjacIce;
    public static Block blockChikuwa;

    public static Item konjac;
    public static Item konjacPoisonous;
    public static Item konjacLava;
    public static Item konjacIce;
    public static Item eggBoiled;
    public static Item eggBoiledPoisonous;
    public static Item eggExplosive;
    public static Item chikuwa;
    public static Item chikuwaPoisonous;
    public static Item chikuwaMotorbike;
    public static Item chikuwabu;
    public static Item kamaboko;
    public static Item surimi;
    public static Item surimiSeared;
    public static Item sashimi;
    public static Item seafoodSalad;

    public static Item stickObsidian;
    public static Item twilightStone;

    public static Item odenA2;
    public static Item odenA3;
    public static Item odenB1;
    public static Item odenB2;
    public static Item odenB3;
    public static Item odenC1;
    public static Item odenC2;
    public static Item odenD1;
    public static Item odenPoisonous1;
    public static Item odenPoisonous2;
    public static Item odenPoisonous3;
    public static Item odenPoisonousDeadly;
    public static Item odenGolden;
    public static Item odenLava;
    public static Item odenIce;
    public static Item odenWithered;
    public static Item odenStealth;

    public static Item kitchenKnife;
    public static Item muramasa;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        addRecipes();
        registerEntities();
        if(event.getSide().isServer()) {
            return;
        }
        renderItemModels();
        registerEntityRenderings();
        createFiles();
    }

    @SubscribeEvent
    protected static void registerBlocks(RegistryEvent.Register<Block> event) {
        blockKonjac = new BlockKonjac().setUnlocalizedName("blockKonjac");
        blockKonjacPoisonous = new BlockKonjacPoisonous().setUnlocalizedName("blockKonjacPoisonous");
        blockKonjacLava = new BlockKonjacLava().setUnlocalizedName("blockKonjacLava");
        blockKonjacIce = new BlockKonjacIce().setUnlocalizedName("blockKonjacIce");
        blockChikuwa = new UBlockDrops(Material.CLAY, new ItemStack(chikuwa, 4)) {{setSoundType(SoundType.WOOD);}}.setHardness(0.4F).setResistance(0.0F).setUnlocalizedName("blockChikuwa").setCreativeTab(Oden.TAB_ODEN);

        GameRegistry.register(blockKonjac, resource.createResourceLocation("konjac_block"));
        GameRegistry.register(blockKonjacPoisonous, resource.createResourceLocation("konjac_poisonous_block"));
        GameRegistry.register(blockKonjacLava, resource.createResourceLocation("konjac_lava_block"));
        GameRegistry.register(blockKonjacIce, resource.createResourceLocation("konjac_ice_block"));
        GameRegistry.register(blockChikuwa, resource.createResourceLocation("chikuwa_block"));
    }

    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        konjac = new ItemFood(5, 0.3F, false).setUnlocalizedName("konjac").setCreativeTab(Oden.TAB_ODEN);
        konjacPoisonous = new UItemFood(-3, 0.3F, false).setPotionEffect(new PotionEffect(MobEffects.POISON, 300, 0), 1.0F).addPotionEffect(new PotionEffect(MobEffects.HASTE, 2400, 0), 1.0F)
                .setUnlocalizedName("konjacPoisonous").setCreativeTab(Oden.TAB_ODEN);
        konjacLava = new UItemFood(4, 0.3F, false).setPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1200, 1), 0.95F).setFire(10, 1.0F).setAlwaysEdible()
                .setUnlocalizedName("konjacLava").setCreativeTab(Oden.TAB_ODEN);
        konjacIce = new UItemFood(3, 0.0F, false).setPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 1), 1.0F).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 1200, 0), 0.95F).setAlwaysEdible()
                .setUnlocalizedName("konjacIce").setCreativeTab(Oden.TAB_ODEN);
        chikuwa = new ItemFood(4, 0.3F, false).setUnlocalizedName("chikuwa").setCreativeTab(Oden.TAB_ODEN);
        chikuwaPoisonous = new UItemFood(-2, 0.3F, false).setPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 0), 1.0F).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 1200, 0), 1.0F)
                .setUnlocalizedName("chikuwaPoisonous").setCreativeTab(Oden.TAB_ODEN);
        chikuwaMotorbike = new UItemFood(3, 0.3F, false).setPotionEffect(new PotionEffect(MobEffects.SPEED, 300, 6), 1.0F).setAlwaysEdible()
                .setUnlocalizedName("chikuwaMotorbike").setCreativeTab(Oden.TAB_ODEN);
        chikuwabu = new ItemFood(7, 1.2F, false).setUnlocalizedName("chikuwabu").setCreativeTab(Oden.TAB_ODEN);
        eggBoiled = new ItemFood(5, 0.6F, false).setUnlocalizedName("eggBoiled").setCreativeTab(Oden.TAB_ODEN);
        eggBoiledPoisonous = new UItemFood(-3, 0.6F, false).setPotionEffect(new PotionEffect(MobEffects.STRENGTH, 600, 0), 1.0F).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 600, 0), 1.0F)
                .setUnlocalizedName("eggBoiledPoisonous").setCreativeTab(Oden.TAB_ODEN);
        eggExplosive = new ItemEggExplosive().setUnlocalizedName("eggExplosive").setCreativeTab(Oden.TAB_ODEN);
        kamaboko = new ItemFood(3, 0.3F, false).setUnlocalizedName("kamaboko").setCreativeTab(Oden.TAB_ODEN);
        surimi = new Item().setUnlocalizedName("surimi").setCreativeTab(Oden.TAB_ODEN);
        surimiSeared = new ItemFood(1, 0.2F, false).setUnlocalizedName("surimiSeared").setCreativeTab(Oden.TAB_ODEN);
        sashimi = new ItemFood(2, 0.3F, false).setUnlocalizedName("sashimi").setCreativeTab(Oden.TAB_ODEN);
        seafoodSalad = new ItemSoup(7).setUnlocalizedName("seafoodSalad").setCreativeTab(Oden.TAB_ODEN);

        stickObsidian = new Item().setUnlocalizedName("stickObsidian").setCreativeTab(Oden.TAB_ODEN);
        twilightStone = new Item().setUnlocalizedName("twilightStone").setCreativeTab(Oden.TAB_ODEN);

        Item.ToolMaterial toolMaterialOdenA1 = EnumHelper.addToolMaterial("odenA1", 1, 50, 1.0F, 4.0F, 20);
        Item.ToolMaterial toolMaterialOdenA2 = EnumHelper.addToolMaterial("odenA2", 0, 50, 1.0F, 2.0F, 20);
        Item.ToolMaterial toolMaterialOdenA3 = EnumHelper.addToolMaterial("odenA3", 0, 50, 1.0F, 0.0F, 20);
        Item.ToolMaterial toolMaterialOdenB1 = EnumHelper.addToolMaterial("odenB1", 1, 40, 1.0F, 4.0F, 20);
        Item.ToolMaterial toolMaterialOdenB2 = EnumHelper.addToolMaterial("odenB2", 0, 40, 1.0F, 2.0F, 20);
        Item.ToolMaterial toolMaterialOdenB3 = EnumHelper.addToolMaterial("odenB3", 0, 40, 1.0F, 1.0F, 20);
        Item.ToolMaterial toolMaterialOdenC1 = EnumHelper.addToolMaterial("odenC1", 1, 50, 1.0F, 6.0F, 20);
        Item.ToolMaterial toolMaterialOdenC2 = EnumHelper.addToolMaterial("odenC2", 0, 50, 1.0F, 4.0F, 20);
        Item.ToolMaterial toolMaterialOdenD1 = EnumHelper.addToolMaterial("odenD1", 0, 50, 1.0F, 3.0F, 20);
        Item.ToolMaterial toolMaterialPoisonousOden1 = EnumHelper.addToolMaterial("poisonous_oden1", 1, 40, 1.0F, 2.0F, 20);
        Item.ToolMaterial toolMaterialPoisonousOden2 = EnumHelper.addToolMaterial("poisonous_oden2", 0, 35, 1.0F, 1.0F, 20);
        Item.ToolMaterial toolMaterialPoisonousOden3 = EnumHelper.addToolMaterial("poisonous_oden3", 0, 30, 1.0F, 0.0F, 20);
        Item.ToolMaterial toolMaterialDeadlyPoisonousOden = EnumHelper.addToolMaterial("deadly_poisonous_oden", 0, 60, 1.0F, 1.0F, 20);
        Item.ToolMaterial toolMaterialGoldenOden = EnumHelper.addToolMaterial("golden_oden", 2, 40, 1.0F, 8.0F, 20);
        Item.ToolMaterial toolMaterialLavaOden = EnumHelper.addToolMaterial("oden", 3, 80, 3.0F, 5.0F, 20);
        Item.ToolMaterial toolMaterialIceOden = EnumHelper.addToolMaterial("ice_oden", 3, 100, 3.0F, 3.0F, 20);
        Item.ToolMaterial toolMaterialWitheredOden = EnumHelper.addToolMaterial("withered_oden", 3, 120, 3.0F, 3.0F, 20);
        Item.ToolMaterial toolMaterialStealthOden = EnumHelper.addToolMaterial("stealth_oden", 0, 10, 1.0F, -3.0F, 20);

        Item.ToolMaterial toolMaterialIron = EnumHelper.addToolMaterial("knife", 2, 60, 6.5F, 1.5F, 15);
        Item.ToolMaterial toolMaterialMuramasa = EnumHelper.addToolMaterial("knife", 3, 180, 10.0F, 4.0F, 8);

        toolMaterialIron.setRepairItem(new ItemStack(Items.IRON_INGOT));
        toolMaterialMuramasa.setRepairItem(new ItemStack(twilightStone));

        kitchenKnife = new ItemKitchenKnife(toolMaterialIron).setUnlocalizedName("kitchenKnife").setCreativeTab(Oden.TAB_ODEN);
        muramasa = new ItemKitchenKnife(toolMaterialMuramasa).setUnlocalizedName("muramasa").setCreativeTab(Oden.TAB_ODEN);

        odenA3 = new ItemOdenA3(toolMaterialOdenA3, 6, 1.0F).setRestItem(new ItemStack(Items.STICK)).setUnlocalizedName("odenA3");
        odenA2 = new ItemOdenA2(toolMaterialOdenA2, 7, 1.0F).setRestItem(new ItemStack(odenA3)).setUnlocalizedName("odenA2");
        odenA1 = new ItemOdenA1(toolMaterialOdenA1, 8, 1.0F).setRestItem(new ItemStack(odenA2)).setUnlocalizedName("odenA1");
        odenB3 = new ItemOdenB3(toolMaterialOdenB3, 8, 1.0F).setRestItem(new ItemStack(Items.STICK)).setUnlocalizedName("odenB3");
        odenB2 = new ItemOdenB2(toolMaterialOdenB2, 9, 1.0F).setRestItem(new ItemStack(odenB3)).setUnlocalizedName("odenB2");
        odenB1 = new ItemOdenB1(toolMaterialOdenB1, 10, 1.0F).setRestItem(new ItemStack(odenB2)).setUnlocalizedName("odenB1");
        odenC2 = new ItemOdenC2(toolMaterialOdenC2, 6, 1.0F).setRestItem(new ItemStack(odenA3)).setUnlocalizedName("odenC2");
        odenC1 = new ItemOdenC1(toolMaterialOdenC1, 6, 1.0F).setRestItem(new ItemStack(odenC2)).setUnlocalizedName("odenC1");
        odenD1 = new ItemOdenD1(toolMaterialOdenD1, 8, 1.0F).setRestItem(new ItemStack(odenA2)).setUnlocalizedName("odenD1");
        odenPoisonous3 = new ItemOdenPoison3(toolMaterialPoisonousOden3, 4, 1.0F).setRestItem(new ItemStack(Items.STICK)).setUnlocalizedName("odenPoisonous3");
        odenPoisonous2 = new ItemOdenPoison2(toolMaterialPoisonousOden2, 5, 1.0F).setRestItem(new ItemStack(odenPoisonous3)).setUnlocalizedName("odenPoisonous2");
        odenPoisonous1 = new ItemOdenPoison1(toolMaterialPoisonousOden1, 6, 1.0F).setRestItem(new ItemStack(odenPoisonous2)).setUnlocalizedName("odenPoisonous1");
        odenPoisonousDeadly = new ItemOdenPoisonDeadly(toolMaterialDeadlyPoisonousOden, 0, 0.1F).setRestItem(new ItemStack(stickObsidian)).setUnlocalizedName("odenPoisonousDeadly");
        odenGolden = new ItemOdenGold(toolMaterialGoldenOden, 12, 1.2F).setRestItem(new ItemStack(Items.BLAZE_ROD)).setUnlocalizedName("odenGolden");
        odenLava = new ItemOdenLava(toolMaterialLavaOden, 8, 1.2F).setRestItem(new ItemStack(stickObsidian)).setUnlocalizedName("odenLava");
        odenIce = new ItemOdenIce(toolMaterialIceOden, 6, -0.2F).setRestItem(new ItemStack(stickObsidian)).setUnlocalizedName("odenIce");
        odenWithered = new ItemOdenWithered(toolMaterialWitheredOden, -5, 1.0F).setRestItem(new ItemStack(stickObsidian)).setUnlocalizedName("odenWithered");
        odenStealth = new ItemOdenStealth(toolMaterialStealthOden, 6, 0.0F).setRestItem(new ItemStack(stickObsidian)).setUnlocalizedName("odenStealth");

        GameRegistry.register(new ItemBlock(blockKonjac), blockKonjac.getRegistryName());
        GameRegistry.register(new ItemBlock(blockKonjacPoisonous), blockKonjacPoisonous.getRegistryName());
        GameRegistry.register(new ItemBlock(blockKonjacLava), blockKonjacLava.getRegistryName());
        GameRegistry.register(new ItemBlock(blockKonjacIce), blockKonjacIce.getRegistryName());
        GameRegistry.register(new ItemBlock(blockChikuwa), blockChikuwa.getRegistryName());

        GameRegistry.register(konjac, resource.createResourceLocation("konjac"));
        GameRegistry.register(konjacPoisonous, resource.createResourceLocation("konjac_poisonous"));
        GameRegistry.register(konjacLava, resource.createResourceLocation("konjac_lava"));
        GameRegistry.register(konjacIce, resource.createResourceLocation("konjac_ice"));
        GameRegistry.register(eggBoiled, resource.createResourceLocation("egg_boiled"));
        GameRegistry.register(eggBoiledPoisonous, resource.createResourceLocation("egg_boiled_poisonous"));
        GameRegistry.register(eggExplosive, resource.createResourceLocation("egg_explosive"));
        GameRegistry.register(chikuwa, resource.createResourceLocation("chikuwa"));
        GameRegistry.register(chikuwaPoisonous, resource.createResourceLocation("chikuwa_poisonous"));
        GameRegistry.register(chikuwaMotorbike, resource.createResourceLocation("chikuwa_motorbike"));
        GameRegistry.register(chikuwabu, resource.createResourceLocation("chikuwabu"));
        GameRegistry.register(kamaboko, resource.createResourceLocation("kamaboko"));
        GameRegistry.register(surimi, resource.createResourceLocation("surimi"));
        GameRegistry.register(surimiSeared, resource.createResourceLocation("surimi_seared"));
        GameRegistry.register(sashimi, resource.createResourceLocation("sashimi"));
        GameRegistry.register(seafoodSalad, resource.createResourceLocation("seafood_salad"));

        GameRegistry.register(stickObsidian, resource.createResourceLocation("stick_obsidian"));
        GameRegistry.register(twilightStone, resource.createResourceLocation("twilight_stone"));

        GameRegistry.register(odenA1, resource.createResourceLocation("oden_a_1"));
        GameRegistry.register(odenA2, resource.createResourceLocation("oden_a_2"));
        GameRegistry.register(odenA3, resource.createResourceLocation("oden_a_3"));
        GameRegistry.register(odenB1, resource.createResourceLocation("oden_b_1"));
        GameRegistry.register(odenB2, resource.createResourceLocation("oden_b_2"));
        GameRegistry.register(odenB3, resource.createResourceLocation("oden_b_3"));
        GameRegistry.register(odenC1, resource.createResourceLocation("oden_c_1"));
        GameRegistry.register(odenC2, resource.createResourceLocation("oden_c_2"));
        GameRegistry.register(odenD1, resource.createResourceLocation("oden_d_1"));
        GameRegistry.register(odenPoisonous1, resource.createResourceLocation("oden_poisonous_1"));
        GameRegistry.register(odenPoisonous2, resource.createResourceLocation("oden_poisonous_2"));
        GameRegistry.register(odenPoisonous3, resource.createResourceLocation("oden_poisonous_3"));
        GameRegistry.register(odenPoisonousDeadly, resource.createResourceLocation("oden_poisonous_deadly"));
        GameRegistry.register(odenGolden, resource.createResourceLocation("oden_golden"));
        GameRegistry.register(odenLava, resource.createResourceLocation("oden_lava"));
        GameRegistry.register(odenIce, resource.createResourceLocation("oden_ice"));
        GameRegistry.register(odenWithered, resource.createResourceLocation("oden_withered"));
        GameRegistry.register(odenStealth, resource.createResourceLocation("oden_stealth"));

        GameRegistry.register(kitchenKnife, resource.createResourceLocation("kitchen_knife"));
        GameRegistry.register(muramasa, resource.createResourceLocation("muramasa"));

        UOreDictionary.registerOre(CategoryTypes.COOKED, "konjac", konjac);
        UOreDictionary.registerOre(CategoryTypes.COOKED, "egg", eggBoiled);
        UOreDictionary.registerOre(CategoryTypes.COOKED, "chikuwa", chikuwa);
        UOreDictionary.registerOre(CategoryTypes.COOKED, "chikuwabu", chikuwabu);
        UOreDictionary.registerOre(CategoryTypes.COOKED, "kamaboko", kamaboko);
        UOreDictionary.registerOre(CategoryTypes.COOKED, "surimi", surimi);
        UOreDictionary.registerOre(CategoryTypes.COOKED, "surimiSeared", surimiSeared);
        UOreDictionary.registerOre(CategoryTypes.COOKED, "sashimi", sashimi);
        UOreDictionary.registerOre(CategoryTypes.COOKED, "seafoodSalad", seafoodSalad);

        OreDictionary.registerOre("stickObsidian", stickObsidian);
    }

    public static void addRecipes() {
        // food block
        GameRegistry.addRecipe(new ItemStack(blockKonjac), "XX", "XX",
                'X', konjac);
        GameRegistry.addRecipe(new ItemStack(blockKonjacPoisonous), "XX", "XX",
                'X', konjacPoisonous);
        GameRegistry.addRecipe(new ItemStack(blockKonjacLava), "XX", "XX",
                'X', konjacLava);
        GameRegistry.addRecipe(new ItemStack(blockKonjacIce), "XX", "XX",
                'X', konjacIce);
        GameRegistry.addRecipe(new ItemStack(blockChikuwa), "XX", "XX",
                'X', chikuwa);
        GameRegistry.addShapelessRecipe(new ItemStack(konjac, 4),
                blockKonjac);
        GameRegistry.addShapelessRecipe(new ItemStack(konjacPoisonous, 4),
                blockKonjacPoisonous);
        GameRegistry.addShapelessRecipe(new ItemStack(konjacLava, 4),
                blockKonjacLava);
        GameRegistry.addShapelessRecipe(new ItemStack(konjacIce, 4),
                blockKonjacIce);
        GameRegistry.addShapelessRecipe(new ItemStack(chikuwa, 4),
                blockChikuwa);
        // food item
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(konjac),
                UOreDictionary.OREDICT_POTATO, UOreDictionary.OREDICT_SLIME_BALL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(konjacPoisonous),
                Items.POISONOUS_POTATO, UOreDictionary.OREDICT_SLIME_BALL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(konjacLava),
                UOreDictionary.OREDICT_POTATO, Items.MAGMA_CREAM));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(konjacIce),
                UOreDictionary.OREDICT_POTATO, Items.SNOWBALL));
        GameRegistry.addRecipe(new ItemStack(chikuwa, 2), "XXX",
                'X', surimi);
        GameRegistry.addRecipe(new ItemStack(chikuwa, 2), "X", "X", "X",
                'X', surimi);
        GameRegistry.addRecipe(new ItemStack(chikuwaPoisonous), "XYX",
                'X', surimi, 'Y', Items.SPIDER_EYE);
        GameRegistry.addRecipe(new ItemStack(chikuwaPoisonous), "X", "Y", "X",
                'X', surimi, 'Y', Items.SPIDER_EYE);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(chikuwaMotorbike), "XYX",
                'X', surimi, 'Y', UOreDictionary.OREDICT_QUARTZ));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(chikuwaMotorbike), "X", "Y", "X",
                'X', surimi, 'Y', UOreDictionary.OREDICT_QUARTZ));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(chikuwabu), " X ", "X X", " X ",
                'X', UOreDictionary.OREDICT_WHEAT));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(eggBoiled, 7),
                UOreDictionary.OREDICT_EGG, UOreDictionary.OREDICT_EGG, UOreDictionary.OREDICT_EGG,
                UOreDictionary.OREDICT_EGG, UOreDictionary.OREDICT_EGG, UOreDictionary.OREDICT_EGG,
                UOreDictionary.OREDICT_EGG, Items.LAVA_BUCKET, Items.WATER_BUCKET));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(eggBoiledPoisonous, 7),
                UOreDictionary.OREDICT_EGG, UOreDictionary.OREDICT_EGG, UOreDictionary.OREDICT_EGG,
                UOreDictionary.OREDICT_EGG, UOreDictionary.OREDICT_EGG, UOreDictionary.OREDICT_EGG,
                UOreDictionary.OREDICT_EGG, Items.LAVA_BUCKET, UOreDictionary.OREDICT_GUNPOWDER));
        GameRegistry.addRecipe(new ItemStack(eggExplosive, 8), "EEE", "ELE", "EEE",
                'E', eggBoiled, 'L', Items.LAVA_BUCKET);
        GameRegistry.addRecipe(new ItemStack(kamaboko), "XX",
                'X', surimi);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(surimi, 3),
                UOreDictionary.OREDICT_COOKED_FISH, new ItemStack(kitchenKnife, 1, OreDictionary.WILDCARD_VALUE)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(sashimi, 3),
                UOreDictionary.OREDICT_FISH, new ItemStack(kitchenKnife, 1, OreDictionary.WILDCARD_VALUE)));
        GameRegistry.addShapelessRecipe(new ItemStack(seafoodSalad),
                sashimi, sashimi, sashimi, new ItemStack(Blocks.TALLGRASS, 1, BlockTallGrass.EnumType.GRASS.getMeta()), Items.BOWL);
        // material
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(stickObsidian, 4),
                UOreDictionary.OREDICT_OBSIDIAN, new ItemStack(muramasa, 1, OreDictionary.WILDCARD_VALUE)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(twilightStone), "PRP", "RER", "PRP",
                'E', UOreDictionary.OREDICT_EMERALD, 'P', UOreDictionary.OREDICT_ENDER_PEARL, 'R', UOreDictionary.OREDICT_REDSTONE));
        // oden
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenA1), "  K", " CE", "S  ",
                'C', chikuwa, 'E', eggBoiled, 'K', konjac, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenA2), " CE", "S  ",
                'C', chikuwa, 'E', eggBoiled, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenA3), " C", "S ",
                'C', chikuwa, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenB1), "  K", " CE", "S  ",
                'C', chikuwabu, 'E', eggBoiled, 'K', konjac, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenB2), " CE", "S  ",
                'C', chikuwabu, 'E', eggBoiled, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenB3), " C", "S ",
                'C', chikuwabu, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenC1), "  K", " CE", "S  ",
                'C', chikuwa, 'E', eggExplosive, 'K', konjac, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenC2), " CE", "S  ",
                'C', chikuwa, 'E', eggExplosive, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenD1), "  K", " CE", "S  ",
                'C', chikuwa, 'E', eggBoiled, 'K', UOreDictionary.OREDICT_CARROT, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenPoisonous1), "  K", " CE", "S  ",
                'K', konjacPoisonous, 'E', eggBoiledPoisonous, 'C', chikuwaPoisonous, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenPoisonous2), " CE", "S  ",
                'E', eggBoiledPoisonous, 'C', chikuwaPoisonous, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenPoisonous3), " C", "S ",
                'C', chikuwaPoisonous, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenPoisonousDeadly), "ZZZ", "ZOZ", "ZSZ",
                'Z', Items.ROTTEN_FLESH, 'O', new ItemStack(odenPoisonous1), 'S', "stickObsidian"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenGolden), "GGG", "GOG", "GSG",
                'G', UOreDictionary.OREDICT_GOLD_NUGGET, 'O', new ItemStack(odenA1), 'S', Items.BLAZE_ROD));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenLava), "L", "O", "S",
                'L', Items.LAVA_BUCKET, 'O', new ItemStack(odenA1), 'S', "stickObsidian"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenIce), "I", "O", "S",
                'I', Blocks.ICE, 'O', new ItemStack(odenA1), 'S', "stickObsidian"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenWithered), "N", "O", "S",
                'N', UOreDictionary.OREDICT_NETHER_STAR, 'O', new ItemStack(odenA1), 'S', "stickObsidian"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(odenStealth), "EGE", "GOG", " S ",
                'G', UOreDictionary.OREDICT_GLASS, 'O', new ItemStack(odenA1), 'E', UOreDictionary.OREDICT_EMERALD, 'S', "stickObsidian"));
        // tool
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(kitchenKnife), "I ", " S",
                'I', UOreDictionary.OREDICT_IRON_INGOT, 'S', UOreDictionary.OREDICT_STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(muramasa), "T ", "CS",
                'T', twilightStone, 'C', Items.CLAY_BALL, 'S', UOreDictionary.OREDICT_STICK));

        GameRegistry.addSmelting(surimi, new ItemStack(surimiSeared), 0.1F);
        GameRegistry.addSmelting(odenIce, new ItemStack(odenA1), 0.5F);
    }

    public static void registerEntities() {
        UEntityRegistry.registerModEntity(resource, EntityEggExplosive.class, "ExplosiveEgg", 0, instance);
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemModels() {
        UModelLoader.setCustomModelResourceLocation(blockKonjac);
        UModelLoader.setCustomModelResourceLocation(blockKonjacPoisonous);
        UModelLoader.setCustomModelResourceLocation(blockKonjacLava);
        UModelLoader.setCustomModelResourceLocation(blockKonjacIce);
        UModelLoader.setCustomModelResourceLocation(blockChikuwa);

        UModelLoader.setCustomModelResourceLocation(konjac);
        UModelLoader.setCustomModelResourceLocation(konjacPoisonous);
        UModelLoader.setCustomModelResourceLocation(konjacLava);
        UModelLoader.setCustomModelResourceLocation(konjacIce);
        UModelLoader.setCustomModelResourceLocation(eggBoiled);
        UModelLoader.setCustomModelResourceLocation(eggBoiledPoisonous);
        UModelLoader.setCustomModelResourceLocation(eggExplosive);
        UModelLoader.setCustomModelResourceLocation(chikuwa);
        UModelLoader.setCustomModelResourceLocation(chikuwaPoisonous);
        UModelLoader.setCustomModelResourceLocation(chikuwaMotorbike);
        UModelLoader.setCustomModelResourceLocation(chikuwabu);
        UModelLoader.setCustomModelResourceLocation(kamaboko);
        UModelLoader.setCustomModelResourceLocation(surimi);
        UModelLoader.setCustomModelResourceLocation(surimiSeared);
        UModelLoader.setCustomModelResourceLocation(sashimi);
        UModelLoader.setCustomModelResourceLocation(seafoodSalad);

        UModelLoader.setCustomModelResourceLocation(stickObsidian);
        UModelLoader.setCustomModelResourceLocation(twilightStone);

        UModelLoader.setCustomModelResourceLocation(odenA1);
        UModelLoader.setCustomModelResourceLocation(odenA2);
        UModelLoader.setCustomModelResourceLocation(odenA3);
        UModelLoader.setCustomModelResourceLocation(odenB1);
        UModelLoader.setCustomModelResourceLocation(odenB2);
        UModelLoader.setCustomModelResourceLocation(odenB3);
        UModelLoader.setCustomModelResourceLocation(odenC1);
        UModelLoader.setCustomModelResourceLocation(odenC2);
        UModelLoader.setCustomModelResourceLocation(odenD1);
        UModelLoader.setCustomModelResourceLocation(odenPoisonous1);
        UModelLoader.setCustomModelResourceLocation(odenPoisonous2);
        UModelLoader.setCustomModelResourceLocation(odenPoisonous3);
        UModelLoader.setCustomModelResourceLocation(odenPoisonousDeadly);
        UModelLoader.setCustomModelResourceLocation(odenGolden);
        UModelLoader.setCustomModelResourceLocation(odenLava);
        UModelLoader.setCustomModelResourceLocation(odenIce);
        UModelLoader.setCustomModelResourceLocation(odenWithered);
        UModelLoader.setCustomModelResourceLocation(odenStealth);

        UModelLoader.setCustomModelResourceLocation(kitchenKnife);
        UModelLoader.setCustomModelResourceLocation(muramasa);
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityRenderings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityEggExplosive.class, manager -> new RenderSnowball(manager, eggExplosive, Minecraft.getMinecraft().getRenderItem()));
    }

    public static void createFiles() {
        File project = new File("../1.11.2-Oden");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, TAB_ODEN, "ODEN");
        language.register(LanguageResourceManager.JA_JP, TAB_ODEN, "おでん");

        language.register(LanguageResourceManager.EN_US, blockKonjac, "Konjac Block");
        language.register(LanguageResourceManager.JA_JP, blockKonjac, "こんにゃくブロック");
        language.register(LanguageResourceManager.EN_US, blockKonjacPoisonous, "Poisonous Konjac Block");
        language.register(LanguageResourceManager.JA_JP, blockKonjacPoisonous, "毒入りこんにゃくブロック");
        language.register(LanguageResourceManager.EN_US, blockKonjacLava, "Lava Konjac Block");
        language.register(LanguageResourceManager.JA_JP, blockKonjacLava, "熱々こんにゃくブロック");
        language.register(LanguageResourceManager.EN_US, blockKonjacIce, "Ice Konjac Block");
        language.register(LanguageResourceManager.JA_JP, blockKonjacIce, "冷凍こんにゃくブロック");
        language.register(LanguageResourceManager.EN_US, blockChikuwa, "Chikuwa Block");
        language.register(LanguageResourceManager.JA_JP, blockChikuwa, "ちくわブロック");

        language.register(LanguageResourceManager.EN_US, konjac, "Konjac");
        language.register(LanguageResourceManager.JA_JP, konjac, "こんにゃく");
        language.register(LanguageResourceManager.EN_US, konjacPoisonous, "Poisonous Konjac");
        language.register(LanguageResourceManager.JA_JP, konjacPoisonous, "毒入りこんにゃく");
        language.register(LanguageResourceManager.EN_US, konjacLava, "Lava Konjac");
        language.register(LanguageResourceManager.JA_JP, konjacLava, "熱々こんにゃく");
        language.register(LanguageResourceManager.EN_US, konjacIce, "Ice Konjac");
        language.register(LanguageResourceManager.JA_JP, konjacIce, "冷凍こんにゃく");
        language.register(LanguageResourceManager.EN_US, eggBoiled, "Boiled Egg");
        language.register(LanguageResourceManager.JA_JP, eggBoiled, "茹で卵");
        language.register(LanguageResourceManager.EN_US, eggBoiledPoisonous, "Poisonous Boiled Egg");
        language.register(LanguageResourceManager.JA_JP, eggBoiledPoisonous, "毒入り茹で卵");
        language.register(LanguageResourceManager.EN_US, eggExplosive, "Explosive Egg");
        language.register(LanguageResourceManager.JA_JP, eggExplosive, "爆発する卵");
        language.register(LanguageResourceManager.EN_US, chikuwa, "Chikuwa");
        language.register(LanguageResourceManager.JA_JP, chikuwa, "ちくわ");
        language.register(LanguageResourceManager.EN_US, chikuwaPoisonous, "Poisonous Chikuwa");
        language.register(LanguageResourceManager.JA_JP, chikuwaPoisonous, "毒入りちくわ");
        language.register(LanguageResourceManager.EN_US, chikuwaMotorbike, "Motorbike Chikuwa");
        language.register(LanguageResourceManager.JA_JP, chikuwaMotorbike, "爆走ちくわ");
        language.register(LanguageResourceManager.EN_US, chikuwabu, "Chikuwabu");
        language.register(LanguageResourceManager.JA_JP, chikuwabu, "ちくわぶ");
        language.register(LanguageResourceManager.EN_US, kamaboko, "Kamaboko");
        language.register(LanguageResourceManager.JA_JP, kamaboko, "かまぼこ");
        language.register(LanguageResourceManager.EN_US, surimi, "Surimi");
        language.register(LanguageResourceManager.JA_JP, surimi, "すり身");
        language.register(LanguageResourceManager.EN_US, surimiSeared, "Seared Surimi");
        language.register(LanguageResourceManager.JA_JP, surimiSeared, "炙りすり身");
        language.register(LanguageResourceManager.EN_US, sashimi, "Sashimi");
        language.register(LanguageResourceManager.JA_JP, sashimi, "刺し身");
        language.register(LanguageResourceManager.EN_US, seafoodSalad, "Seafood Salad");
        language.register(LanguageResourceManager.JA_JP, seafoodSalad, "シーフードサラダ");

        language.register(LanguageResourceManager.EN_US, stickObsidian, "Obsidian Stick");
        language.register(LanguageResourceManager.JA_JP, stickObsidian, "黒曜石の棒");
        language.register(LanguageResourceManager.EN_US, twilightStone, "Twilight Stone");
        language.register(LanguageResourceManager.JA_JP, twilightStone, "黄昏の赤石");

        language.register(LanguageResourceManager.EN_US, odenA1, "ODEN");
        language.register(LanguageResourceManager.JA_JP, odenA1, "おでん");
        language.register(LanguageResourceManager.EN_US, odenA2, "Half Eaten ODEN");
        language.register(LanguageResourceManager.JA_JP, odenA2, "食べかけのおでん");
        language.register(LanguageResourceManager.EN_US, odenA3, "Leftover ODEN");
        language.register(LanguageResourceManager.JA_JP, odenA3, "食べ残しのおでん");
        language.register(LanguageResourceManager.EN_US, odenB1, "ODEN (F)");
        language.register(LanguageResourceManager.JA_JP, odenB1, "おでん(偽)");
        language.register(LanguageResourceManager.EN_US, odenB2, "Half Eaten ODEN (F)");
        language.register(LanguageResourceManager.JA_JP, odenB2, "食べかけのおでん(偽)");
        language.register(LanguageResourceManager.EN_US, odenB3, "Leftover ODEN (F)");
        language.register(LanguageResourceManager.JA_JP, odenB3, "食べ残しのおでん(偽)");
        language.register(LanguageResourceManager.EN_US, odenC1, "Explosive ODEN");
        language.register(LanguageResourceManager.JA_JP, odenC1, "爆発するおでん");
        language.register(LanguageResourceManager.EN_US, odenC2, "Half Eaten Explosive ODEN");
        language.register(LanguageResourceManager.JA_JP, odenC2, "食べかけの爆発するおでん");
        language.register(LanguageResourceManager.EN_US, odenD1, "Different ODEN");
        language.register(LanguageResourceManager.JA_JP, odenD1, "間違ったおでん");
        language.register(LanguageResourceManager.EN_US, odenPoisonous1, "Poisonous ODEN");
        language.register(LanguageResourceManager.JA_JP, odenPoisonous1, "毒入りおでん");
        language.register(LanguageResourceManager.EN_US, odenPoisonous2, "Half Eaten Poisonous ODEN");
        language.register(LanguageResourceManager.JA_JP, odenPoisonous2, "食べかけの毒入りおでん");
        language.register(LanguageResourceManager.EN_US, odenPoisonous3, "Leftover Poisonous ODEN");
        language.register(LanguageResourceManager.JA_JP, odenPoisonous3, "食べ残しの毒入りおでん");
        language.register(LanguageResourceManager.EN_US, odenPoisonousDeadly, "Deadly Poisonous ODEN");
        language.register(LanguageResourceManager.JA_JP, odenPoisonousDeadly, "猛毒入りおでん");
        language.register(LanguageResourceManager.EN_US, odenGolden, "Goldoden");
        language.register(LanguageResourceManager.JA_JP, odenGolden, "ゴールDおデン");
        language.register(LanguageResourceManager.EN_US, odenLava, "Lava ODEN");
        language.register(LanguageResourceManager.JA_JP, odenLava, "熱々おでん");
        language.register(LanguageResourceManager.EN_US, odenIce, "Ice ODEN");
        language.register(LanguageResourceManager.JA_JP, odenIce, "冷凍おでん");
        language.register(LanguageResourceManager.EN_US, odenWithered, "Withered ODEN");
        language.register(LanguageResourceManager.JA_JP, odenWithered, "暗黒おでん");
        language.register(LanguageResourceManager.EN_US, odenStealth, "Stealth ODEN");
        language.register(LanguageResourceManager.JA_JP, odenStealth, "ステルスおでん");

        language.register(LanguageResourceManager.EN_US, kitchenKnife, "Kitchen Knife");
        language.register(LanguageResourceManager.JA_JP, kitchenKnife, "包丁");
        language.register(LanguageResourceManager.EN_US, muramasa, "The Muramasa");
        language.register(LanguageResourceManager.JA_JP, muramasa, "ムラマサ包丁");

        language.register(LanguageResourceManager.EN_US, EntityEggExplosive.class, "Egg of Pig");
        language.register(LanguageResourceManager.JA_JP, EntityEggExplosive.class, "豚の卵");

        ULanguageCreator.createLanguage(project, MOD_ID, language);

        UModelCreator.createBlockstateJson(project, blockKonjac, "eyeq_oden:blocks/konjac");
        UModelCreator.createBlockstateJson(project, blockKonjacPoisonous, "eyeq_oden:blocks/konjac_poisonous");
        UModelCreator.createBlockstateJson(project, blockKonjacLava, "eyeq_oden:blocks/konjac_lava");
        UModelCreator.createBlockstateJson(project, blockKonjacIce, "eyeq_oden:blocks/konjac_ice");
        UModelCreator.createBlockstateJson(project, blockChikuwa, BlockmodelJsonFactory.createBTSBlockmodelJson("block/cube_bottom_top", "eyeq_oden:blocks/chikuwa_top", "eyeq_oden:blocks/chikuwa_top", "eyeq_oden:blocks/chikuwa_side"));

        UModelCreator.createItemJson(project, konjac, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, konjacPoisonous, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, konjacLava, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, konjacIce, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, eggBoiled, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, eggBoiledPoisonous, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, eggExplosive, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, chikuwa, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, chikuwaPoisonous, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, chikuwaMotorbike, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, chikuwabu, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, kamaboko, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, surimi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, surimiSeared, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, sashimi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, seafoodSalad, ItemmodelJsonFactory.ItemmodelParent.GENERATED);

        UModelCreator.createItemJson(project, stickObsidian, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, twilightStone, ItemmodelJsonFactory.ItemmodelParent.GENERATED);

        UModelCreator.createItemJson(project, odenA1, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenA2, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenA3, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenB1, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenB2, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenB3, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenC1, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenC2, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenD1, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenPoisonous1, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenPoisonous2, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenPoisonous3, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenPoisonousDeadly, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenGolden, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenLava, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenIce, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenWithered, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, odenStealth, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);

        UModelCreator.createItemJson(project, kitchenKnife, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
        UModelCreator.createItemJson(project, muramasa, ItemmodelJsonFactory.ItemmodelParent.HANDHELD);
    }
}
