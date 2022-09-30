import math



B = [None, 3,1,1,2] # index corresponding to advertiser
Q = [1,2,3,4,3,3,2,4]

advertiser_query_bid_matrix = [
    [1,1,0.5],
    [4,1,0.75],
    [2,2,0.5],
    [3,2,0.5],
    [1,3,1],
    [3,4,1]
]

def lookup_q(q):
    result = []
    for array in advertiser_query_bid_matrix:
        if array[1] == q:
            result.append(array)
    return result









def select_candidate_generalized_balance(candidates):

    # check if candidate can afford bid
    candidates = [candidate for candidate in candidates if (B[candidate[0]] >= candidate[2])] 
    
    if not candidates:
        return False

    selected_candidate = candidates[0]
    for candidate in candidates:
        if candidate[3][0] > selected_candidate[3][0]:
            selected_candidate = candidate
        elif candidate[3][0] == selected_candidate[3][0]:
            print("tie-breaker?")
            if candidate[0] < selected_candidate[0]: # tie breaker on min advertiser id
                selected_candidate = candidate

    return selected_candidate



print("generalized balance (working):")
# print header
print("| Time | Query | Candidates,bids        | scores                               | b1   | b2 | b3 | b4 | Accu. revenue | Notes       |")
print("|------|-------|------------------------|--------------------------------------|------|----|----|----|---------------|-------------|")

accumulated_revenue = 0
for i, q in enumerate(Q):
    i += 1
    time = i
    candidates = lookup_q(q)
    for i, candidate in enumerate(candidates):
        score = [candidate[2]*(1-math.e**(-1)), f"{candidate[2]}*(1-e^(-1))"]
        candidates[i].append(score)
    selected_candidate = select_candidate_generalized_balance(candidates)
    if not selected_candidate:
        winning_advertiser, winning_bid = 0, 0
    if selected_candidate:
        winning_advertiser = selected_candidate[0]
        winning_bid = selected_candidate[2]
        B[winning_advertiser] -= winning_bid
        accumulated_revenue += winning_bid

    # printing generalized balance
    candidates_print = [f"{candidate[0]},{candidate[2]}" for candidate in candidates]
    # print(f"time: {time}, q: {q}, candidates(a,q,bid,score): {candidates_print}, selected candidate: {selected_candidate} B: {B}, acc.rev.: {accumulated_revenue}")
    scores_print = [f"{candidate[3][1]}={candidate[3][0]}  " for candidate in candidates]
    # print table
    columns = [time, q, candidates_print, scores_print, B[1], B[2], B[3], B[4], accumulated_revenue]
    
    row_string = "|"
    for column in columns:
        row_string += f"{column}|"
    print(row_string)















B = [None, 3,1,1,2] # index corresponding to advertiser
Q = [1,2,3,4,3,3,2,4]




def select_candidate_greedy(candidates): # not working

    # check if candidate can afford bid
    candidates = [candidate for candidate in candidates if (B[candidate[0]] >= candidate[2])] 
    
    if not candidates:
        return False

    largest_b = None

    selected_candidate = candidates[0]

    for candidate in candidates:
        b = B[candidate[0]]
        if b > B[selected_candidate[0]]:
            candidate = selected_candidate
        elif b == largest_b:
            print("tie-breaker on line below?")
            if candidate[0] < selected_candidate[0]: # tie breaker on min advertiser id
                candidate = selected_candidate
    return selected_candidate

print("greedy:")
# print header
print("| Time | Query | Candidates,bids                                       | b1   | b2 | b3 | b4 | Accu. revenue | Notes       |")
print("|------|-------|--------------------------------------|------|----|----|----|---------------|-------------|")

accumulated_revenue = 0
for i, q in enumerate(Q):
    i += 1
    time = i
    candidates = lookup_q(q)
    selected_candidate = select_candidate_greedy(candidates)
    if not selected_candidate:
        winning_advertiser, winning_bid = 0, 0
    if selected_candidate:
        winning_advertiser = selected_candidate[0]
        winning_bid = selected_candidate[2]
        B[winning_advertiser] -= winning_bid
        accumulated_revenue += winning_bid

    # printing 
    candidates_print = [f"{candidate[0]},{candidate[2]}" for candidate in candidates]
    # print table
    columns = [time, q, candidates_print, B[1], B[2], B[3], B[4], accumulated_revenue]
    
    row_string = "|"
    for column in columns:
        row_string += f"{column}|"
    print(row_string)


















advertiser_query_bid_matrix = [
    [1,1,1],
    [4,1,1],
    [2,2,1],
    [3,2,1],
    [1,3,1],
    [3,4,1]
]


B = [None, 3,1,1,2] # index corresponding to advertiser
Q = [1,2,3,4,3,3,2,4]




def select_candidate_greedy(candidates):

    # check if candidate can afford bid
    candidates = [candidate for candidate in candidates if (B[candidate[0]] >= candidate[2])] 
    
    if not candidates:
        return False

    largest_b = None

    selected_candidate = candidates[0]

    for candidate in candidates:
        if candidate[0] < selected_candidate[0]: # tie breaker on min advertiser id
            candidate = selected_candidate
    return selected_candidate

print("greedy: (not working)")
# print header
print("| Time | Query | Candidates,bids                                       | b1   | b2 | b3 | b4 | Accu. revenue | Notes       |")
print("|------|-------|--------------------------------------|------|----|----|----|---------------|-------------|")

accumulated_revenue = 0
for i, q in enumerate(Q):
    i += 1
    time = i
    candidates = lookup_q(q)
    selected_candidate = select_candidate_greedy(candidates)
    if not selected_candidate:
        winning_advertiser, winning_bid = 0, 0
    if selected_candidate:
        winning_advertiser = selected_candidate[0]
        winning_bid = selected_candidate[2]
        B[winning_advertiser] -= winning_bid
        accumulated_revenue += winning_bid

    # printing 
    candidates_print = [f"{candidate[0]},{candidate[2]}" for candidate in candidates]
    # print table
    columns = [time, q, candidates_print, B[1], B[2], B[3], B[4], accumulated_revenue]
    
    row_string = "|"
    for column in columns:
        row_string += f"{column}|"
    print(row_string)


