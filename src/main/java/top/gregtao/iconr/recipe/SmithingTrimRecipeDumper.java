package top.gregtao.iconr.recipe;

import com.google.gson.JsonObject;
import net.minecraft.recipe.SmithingTrimRecipe;
import top.gregtao.iconr.api.IRecipeDumper;
import top.gregtao.iconr.mixin.SmithingTrimRecipeAccessor;
import top.gregtao.iconr.util.IconRUtils;

public class SmithingTrimRecipeDumper implements IRecipeDumper<SmithingTrimRecipe> {

    @Override
    public JsonObject dumpInputs(JsonObject object, SmithingTrimRecipe recipe) {
        SmithingTrimRecipeAccessor accessor = (SmithingTrimRecipeAccessor) recipe;
        object.add("1", IRecipeDumper.fromIngredient(accessor.getBase()));
        object.add("2", IRecipeDumper.fromIngredient(accessor.getAddition()));
        object.add("3", IRecipeDumper.fromIngredient(accessor.getTemplate()));
        return object;
    }

    @Override
    public JsonObject dumpOutputs(JsonObject object, SmithingTrimRecipe recipe) {
        object.add("1", IRecipeDumper.fromItemStack(recipe.getResult(IconRUtils.getRegistryManager())));
        return object;
    }
}
