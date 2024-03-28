package net.megal;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.megal.annotation.*;
import net.megal.annotation.ItemModel.ModelType;
import net.megal.annotation.Recipe.RecipeType;
import net.megal.item.UItems;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.data.client.*;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class UAddDataGen implements DataGeneratorEntrypoint {
	public static HashMap<Item, String> generatableNames = new HashMap<>();
	public static Multimap<ModelType, Item> generatableModels = HashMultimap.create();
	public static Multimap<RecipeType, RecipeInstance> generatableRecipes = HashMultimap.create();

	public static class RecipeInstance {
		public final Item output;
		public final RecipeCategory category;
		public final Ingredient[] ingredients;
		public final float xp;
		public final int ticks;

		public RecipeInstance(Recipe recipe, Item output) {
			this.output = recipe.swapInputOutput() ? readItem(recipe.ingredients()[recipe.type() == RecipeType.SMITHING ? 1 : 0].value()[0]) : output;

			this.category = recipe.category();
			this.ingredients = recipe.swapInputOutput() ?
					new Ingredient[]{Ingredient.ofItems(output)} :
					Arrays.stream(recipe.ingredients()).map(this::readIngredient).toArray(Ingredient[]::new);

			this.xp = recipe.xp();
			this.ticks = recipe.ticks();
		}

		public Item readItem(String id) {
			return Registries.ITEM.get(new Identifier(id));
		}

		public Ingredient readIngredient(SimpleIngredient simpleIngredient) {
			if (simpleIngredient.value()[0].charAt(0) == '#') {
				String id = simpleIngredient.value()[0].substring(1);
				return Ingredient.fromTag(TagKey.of(RegistryKeys.ITEM, new Identifier(id)));
			}

			return Ingredient.ofItems(Arrays.stream(simpleIngredient.value()).map(id -> Registries.ITEM.get(new Identifier(id))).toArray(Item[]::new));
		}
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		Field[] fields = UItems.class.getDeclaredFields();
		for (Field f : fields) {
			Object object;
			try {
				object = f.get(UItems.class);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}

			if (!(object instanceof Item item)) continue;

			Recipe[] recipes = f.getAnnotationsByType(Recipe.class);
			Arrays.stream(recipes).forEach(recipe -> generatableRecipes.put(recipe.type(), new RecipeInstance(recipe, item)));

			ItemModel itemModel = f.getAnnotation(ItemModel.class);
			if (itemModel != null) {
				generatableModels.put(itemModel.value(), item);
			}

			Name name = f.getAnnotation(Name.class);
			if (name != null) {
				generatableNames.put(item, name.value());
			}
		}

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(UNameProvider::new);
		pack.addProvider(UTagProvider::new);
		pack.addProvider(UModelProvider::new);
		pack.addProvider(URecipeProvider::new);
	}

	public static class UNameProvider extends FabricLanguageProvider {
		protected UNameProvider(FabricDataOutput dataOutput) {
			super(dataOutput, "en_us");
		}

		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			generatableNames.forEach((item, name) -> {
				if (name.isEmpty()) {
					Identifier identifier = Registries.ITEM.getId(item);
					StringBuilder nameBuilder = new StringBuilder();

					Arrays.stream(identifier.getPath().replace("_", "& ").split("&")).forEach(word -> nameBuilder.append(StringUtil.capitalize(word)));
					name = nameBuilder.toString();
				}

				translationBuilder.add(item, name);
            });

			try {
				Path existingFilePath = dataOutput.getModContainer().findPath("assets/uselessadditions/lang/en_us.existing.json").get();
				translationBuilder.add(existingFilePath);
			} catch (Exception e) {
				throw new RuntimeException("Couldn't find existing lang file :P", e);
			}
		}
	}

	public static class UTagProvider extends FabricTagProvider.ItemTagProvider {
		public UTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(ItemTags.ARROWS).add(UAdd.arrowTypes.toArray(new Item[0]));
		}
	}

	public static class UModelProvider extends FabricModelProvider {
		public static final Model BOW = item(UAdd.ID, "bow", TextureKey.LAYER0);
		public static final Model PULLED_BOW = item(UAdd.ID, "pulled_bow", TextureKey.LAYER0, TextureKey.LAYER1);
		public static final Model HANDHELD2x = item(UAdd.ID, "handheld2x", TextureKey.LAYER0);

		public UModelProvider(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			generatableModels.get(ModelType.GENERATED).forEach(item -> {
				itemModelGenerator.register(item, Models.GENERATED);
			});

			generatableModels.get(ModelType.ARMOR).forEach(item -> {
				if (item instanceof ArmorItem armorItem) itemModelGenerator.registerArmor(armorItem);
			});

			generatableModels.get(ModelType.HANDHELD).forEach(item -> {
				itemModelGenerator.register(item, Models.HANDHELD);
			});

			generatableModels.get(ModelType.HANDHELD2X).forEach(item -> {
				itemModelGenerator.register(item, HANDHELD2x);
			});

			generatableModels.get(ModelType.BOW).forEach(item -> {
				registerBow(item, itemModelGenerator);
			});

			registerBow(Items.BOW, itemModelGenerator);
		}

		public final void registerBow(Item item, ItemModelGenerator itemModelGenerator) {
			BOW.upload(ModelIds.getItemModelId(item), TextureMap.layer0(item), itemModelGenerator.writer, this::createBowJson);
			for (Item arrow : UAdd.arrowTypes) {
				String arrowId = Registries.ITEM.getId(arrow).getPath();
				Identifier arrowTextureId = new Identifier(UAdd.ID, TextureMap.getId(arrow).getPath());

				for (int i = 0; i < 3; i++) {
					PULLED_BOW.upload(
							ModelIds.getItemModelId(item).withSuffixedPath("_pulling_with_" + arrowId + "_" + i),
							TextureMap.layered(TextureMap.getId(item).withSuffixedPath("_pulling_" + i), arrowTextureId.withSuffixedPath("_pulling_" + i)),
							itemModelGenerator.writer
					);
				}
			}
		}

		public final JsonObject createBowJson(Identifier id, Map<TextureKey, Identifier> textures) {
			JsonObject jsonObject = BOW.createJson(id, textures);
			JsonArray jsonArray = new JsonArray();

			for (Item arrow : UAdd.arrowTypes) {
				String arrowId = Registries.ITEM.getId(arrow).getPath();
				for(int i = 0; i < 3; i++) {
					JsonObject jsonObject2 = new JsonObject();
					JsonObject jsonObject3 = new JsonObject();
					jsonObject3.addProperty("arrow_type", UAdd.arrowTypes.indexOf(arrow) / 100f);
					jsonObject3.addProperty("pulling", 1);
					if (i == 1 || i == 2) jsonObject3.addProperty("pull", i == 1 ? 0.65 : 0.9);
					jsonObject2.add("predicate", jsonObject3);
					jsonObject2.addProperty("model", id.withSuffixedPath("_pulling_with_" + arrowId + "_" + i).toString());
					jsonArray.add(jsonObject2);
				}
			}

			jsonObject.add("overrides", jsonArray);
			return jsonObject;
		}

		private static Model item(String namespace, String parent, TextureKey... requiredTextureKeys) {
			return new Model(Optional.of(new Identifier(namespace, "item/" + parent)), Optional.empty(), requiredTextureKeys);
		}
	}

	public static class URecipeProvider extends FabricRecipeProvider {
		public URecipeProvider(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generate(RecipeExporter exporter) {
			generatableRecipes.get(RecipeType.PACKING).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.input('#', ingredients[0]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, "pack_" + getItemPath(output));
			});

			generatableRecipes.get(RecipeType.PACKING_2X2).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern("##")
						.pattern("##")
						.input('#', ingredients[0]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, "pack_" + getItemPath(output));
			});

			generatableRecipes.get(RecipeType.UNPACKING).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapelessRecipeJsonBuilder recipe = ShapelessRecipeJsonBuilder.create(recipeInstance.category, output, 9)
						.input(ingredients[0]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, "unpack_" + getItemPath(output));
			});

			generatableRecipes.get(RecipeType.UNPACKING_4).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapelessRecipeJsonBuilder recipe = ShapelessRecipeJsonBuilder.create(recipeInstance.category, output, 4)
						.input(ingredients[0]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, "unpack_" + getItemPath(output));
			});

			generatableRecipes.get(RecipeType.SWORD).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern("#")
						.pattern("#")
						.pattern("/")
						.input('/', ingredients[0])
						.input('#', ingredients[1]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output));
			});

			generatableRecipes.get(RecipeType.SHOVEL).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern("#")
						.pattern("/")
						.pattern("/")
						.input('/', ingredients[0])
						.input('#', ingredients[1]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output));
			});

			generatableRecipes.get(RecipeType.PICKAXE).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern("###")
						.pattern(" / ")
						.pattern(" / ")
						.input('/', ingredients[0])
						.input('#', ingredients[1]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output));
			});

			generatableRecipes.get(RecipeType.AXE).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern("##")
						.pattern("#/")
						.pattern(" /")
						.input('/', ingredients[0])
						.input('#', ingredients[1]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output));
			});

			generatableRecipes.get(RecipeType.HOE).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern("##")
						.pattern(" /")
						.pattern(" /")
						.input('/', ingredients[0])
						.input('#', ingredients[1]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output));
			});

			generatableRecipes.get(RecipeType.HAMMER).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern(" # ")
						.pattern(" /#")
						.pattern("/  ")
						.input('/', ingredients[0])
						.input('#', ingredients[1]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output));
			});

			generatableRecipes.get(RecipeType.SCYTHE).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern("##n")
						.pattern(" s#")
						.pattern(" s ")
						.input('s', ingredients[0])
						.input('#', ingredients[1])
						.input('n', ingredients[2]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output));
			});

			generatableRecipes.get(RecipeType.BOW).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern("n#s")
						.pattern("# s")
						.pattern("n#s")
						.input('s', ingredients[0])
						.input('#', ingredients[1])
						.input('n', ingredients[2]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output));
			});

			generatableRecipes.get(RecipeType.ARROW).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				ShapedRecipeJsonBuilder recipe = ShapedRecipeJsonBuilder.create(recipeInstance.category, output)
						.pattern("#")
						.pattern("/")
						.pattern("f")
						.input('/', ingredients[0])
						.input('#', ingredients[1])
						.input('f', ingredients[2]);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output));
			});

			generatableRecipes.get(RecipeType.SMELTING).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				CookingRecipeJsonBuilder recipe = CookingRecipeJsonBuilder.createSmelting(ingredients[0], recipeInstance.category, output, recipeInstance.xp, recipeInstance.ticks);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output) + "_from_smelting");
			});

			generatableRecipes.get(RecipeType.SMOKING).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				CookingRecipeJsonBuilder recipe = CookingRecipeJsonBuilder.createSmoking(ingredients[0], recipeInstance.category, output, recipeInstance.xp, recipeInstance.ticks);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output) + "_from_smoking");
			});

			generatableRecipes.get(RecipeType.BLASTING).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				CookingRecipeJsonBuilder recipe = CookingRecipeJsonBuilder.createBlasting(ingredients[0], recipeInstance.category, output, recipeInstance.xp, recipeInstance.ticks);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output) + "_from_blasting");
			});

			generatableRecipes.get(RecipeType.SMITHING).forEach(recipeInstance -> {
				Ingredient[] ingredients = recipeInstance.ingredients;
				Item output = recipeInstance.output;

				SmithingTransformRecipeJsonBuilder recipe = SmithingTransformRecipeJsonBuilder.create(ingredients[0], ingredients[1], ingredients[2], recipeInstance.category, output);

				Arrays.stream(ingredients).forEach(ingredient -> {
					Arrays.stream(ingredient.getMatchingStacks()).forEach(stack -> {
						Item item = stack.getItem();
						recipe.criterion(hasItem(item), conditionsFromItem(item));
					});
				});

				recipe.offerTo(exporter, getItemPath(output));
			});
		}
	}
}
