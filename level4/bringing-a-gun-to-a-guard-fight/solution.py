import math

def answer(dimensions, your_position, guard_position, distance):
    hit_yourself = get_angles(dimensions, your_position, your_position, distance)
    hit_guard = get_angles(dimensions, your_position, guard_position, distance)
    solutions = 0
    
    for key in hit_guard:
        if key not in hit_yourself or hit_guard[key] < hit_yourself[key]:
            solutions += 1
            
    return solutions

def distance(p1, p2):
    return math.sqrt((p2[0] - p1[0]) ** 2 + (p2[1] - p1[1]) ** 2)

def angle(p1, p2):
    return math.atan2(p2[1] - p1[1], p2[0] - p1[0])

def cartesian_product(series):
    points = []
    for i in series[0]:
        for j in series[1]:
            points.append([i, j])
    return points

def generate_series(dimensions, your_position, guard_position, distance, i):
    minimum = your_position[i] - distance
    maximum = your_position[i] + distance
    segment = [dimensions[i] - guard_position[i], guard_position[i]]
    series = []
    
    current = guard_position[i]
    segment_index = 0
    while current <= maximum:
        series.append(current)
        current += 2 * segment[segment_index]
        segment_index = (segment_index + 1) % 2
    
    current = guard_position[i]
    segment_index = 1
    while current >= minimum:
        series.append(current)
        current -= 2 * segment[segment_index]
        segment_index = (segment_index + 1) % 2
    
    return series

def generate_all_series(dimensions, your_position, guard_position, distance):
    series = []
    for i in range(2):
        series.append(generate_series(dimensions, your_position, guard_position, distance, i))
    return series

def get_angles(dimensions, your_position, guard_position, distance):
    polar_map = {}
    for point in cartesian_product(generate_all_series(dimensions, your_position, guard_position, distance)):
        r = distance(point, your_position)
        angle_value = angle(point, your_position)
        if point != your_position and r <= distance:
            polar_map[angle_value] = min(polar_map.get(angle_value, float('inf')), r)
    return polar_map
