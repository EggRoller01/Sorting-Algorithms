import csv
import matplotlib.pyplot as plt
import numpy as np


def analyze_results(results_file, alg_filter=None): 
    # Read results from CSV
    results = {}
    all_sizes = []
    all_times = []
    
    with open(results_file, 'r') as f:
        reader = csv.DictReader(f)
        for row in reader:
            alg = row['Algorithm']
            size = int(row['ArraySize'])
            time = float(row['Time_ms'])
            if alg not in results:
                results[alg] = []
            results[alg].append((size, time))
            all_sizes.append(size)
            all_times.append(time)
    
    # Plotting
    plt.figure(figsize=(10, 6))
    for alg, data in results.items():
        if alg_filter and alg not in alg_filter:
            continue
        data.sort()  # Sort by input size
        sizes, times = zip(*data)
        
        plt.plot(sizes, times, marker='o', markersize=3, linewidth=1.3, label=alg)

    # Reference curves for O(N^2) and O(N log N) and O(N)
    if all_sizes and all_times:
        min_size = min(all_sizes)
        max_size = max(all_sizes)
        
        min_time = max(min(all_times), 0.001) # avoid log(0) issues if min_time is 0

        x_ref = np.linspace(min_size, max_size, 100)
        
        # Anchoring and calculation for O(N^2)
        # We compute C such that: C * min_size^2 = min_time
        c_n2 = min_time / (min_size**2)
        y_n2 = c_n2 * (x_ref**2)
        
        # Anchoring and calculation for O(N log N)
        # We compute C such that: C * (min_size * log2(min_size)) = min_time
        c_nlogn = min_time / (min_size * np.log2(min_size))
        y_nlogn = c_nlogn * (x_ref * np.log2(x_ref))

        # Anchoring and calculation for O(N)
        # We compute C such that: C * min_size = min_time
        c_n = min_time / min_size
        y_n = c_n * x_ref

        # plt.plot(x_ref, y_n2, 'r--', alpha=0.5, label='$O(N^2)$')
        plt.plot(x_ref, y_nlogn, 'g--', alpha=0.5, label='$O(N \log N)$')
        plt.plot(x_ref, y_n, 'b--', alpha=0.5, label='$O(N)$')

    plt.xlabel('Input Size')
    plt.ylabel('Time (ms)')
    plt.title('Sorting Algorithm Performance')
    plt.legend()
    
    plt.xscale('log')
    # plt.yscale('log')
    
    # plt.grid(True, which="both", ls="-", alpha=0.1) 
    
    plt.show()

if __name__ == "__main__":
    analyze_results('benchmark_results.csv')