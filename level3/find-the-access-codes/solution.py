def answer(l):
    result = 0
    
    if len(l) < 3:
        return 0
    
    for i in range(1, len(l) - 1):
        count_x = 0
        count_z = 0
        
        for j in range(i):
            if l[i] % l[j] == 0:
                count_x += 1
                
        for k in range(i + 1, len(l)):
            if l[k] % l[i] == 0:
                count_z += 1
                
        result += count_x * count_z
        
    return result
