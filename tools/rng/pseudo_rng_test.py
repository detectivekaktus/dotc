from random import random


def get_proc_chance(base_chance: float, times_without_proc: int) -> float:
    return base_chance if times_without_proc == 0 else base_chance * times_without_proc


def get_average_proc_chance(base_chance: float) -> float:
    attacks = 0
    procs = 0
    times_without_proc = 0

    for _ in range(1, 1000000):
        attacks += 1
        times_without_proc += 1
        proc_chance = get_proc_chance(base_chance, times_without_proc)

        if random() < proc_chance:
            procs += 1
            times_without_proc = 0

    avg = procs / attacks
    return avg


def main() -> None:
    print("=== PROC REPORT ===")

    # These are average proc chances after 1.000.000 attacks
    #          10%    20%   25%    33.3%  40%   50%    80%
    chances = [0.016, 0.06, 0.088, 0.145, 0.21, 0.311, 0.755]
    for chance in chances:
        avg = get_average_proc_chance(chance)
        print(f"C = {chance} averages to: {round(avg * 100, 5)}%")


if __name__ == "__main__":
    main()
