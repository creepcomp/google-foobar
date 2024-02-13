def answer(data, n):
    count_dict = {}
    for num in data:
        count_dict[num] = count_dict.get(num, 0) + 1
    
    filtered_data = [num for num in data if count_dict[num] <= n]
    
    return filtered_data
