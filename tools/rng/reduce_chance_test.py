MAX_UNSCALED_CHANCE = 0.33

def reduce_chance(chance: float) -> float:
    if chance <= MAX_UNSCALED_CHANCE:
        return chance
    diff = chance - MAX_UNSCALED_CHANCE
    return MAX_UNSCALED_CHANCE + diff * diff


def main() -> None:
    print("====REDUCTION REPORT====")
    for i in range(0, 100, 5):
        chance = i / 100
        reduced_chance = reduce_chance(chance)
        print(f"Chance {chance} got reduced to {reduced_chance}")


if __name__ == "__main__":
    main()