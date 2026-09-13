package top.gregtao.iconr.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import top.gregtao.iconr.util.IconRUtils;

public interface IRecipeDumper<T extends Recipe<?>> {

    default JsonObject dump(T recipe, Identifier id) {
        JsonObject object = new JsonObject();
        object.addProperty("type", IconRUtils.getRecipeTypeId(recipe.getSerializer()));
        object.addProperty("name", id.toString());
        object.add("input", this.dumpInputs(new JsonObject(), recipe));
        object.add("output", this.dumpOutputs(new JsonObject(), recipe));
        dumpExtraInfo(object, recipe);
        return object;
    }

    JsonObject dumpInputs(JsonObject object, T recipe);

    JsonObject dumpOutputs(JsonObject object, T recipe);

    default void dumpExtraInfo(JsonObject object, T recipe) {}

    static JsonObject fromItemStack(ItemStack itemStack) {
        JsonObject object = new JsonObject();
        object.addProperty("item", Registries.ITEM.getId(itemStack.getItem()).toString());
        object.addProperty("count", itemStack.getCount());
        return object;
    }

    static JsonElement fromIngredient(Ingredient ingredient) {
        return Ingredient.ALLOW_EMPTY_CODEC.encodeStart(JsonOps.INSTANCE, ingredient).getOrThrow();
    }

}
