from exp_modules.expService import get_summary_ranked_sigma,get_summary_unranked_sigma,getSummary
from rouge import Rouge
from common_utils.FileReader import read_files
import json

rouge = Rouge()

# evaluation_dictionary = read_files()
# print(len(evaluation_dictionary['documents']))
# print(len(evaluation_dictionary['summary']))

# results = {}
# results_for_excell={}
# summary_collection = {}
# for index in range(len(evaluation_dictionary['documents'])):
#     print(index)
    
#     ind_doc_summary={}
#     ind_doc_summary[f'document'] = evaluation_dictionary['documents'][index]
    
#     ############################################################
#     summary = getSummary(evaluation_dictionary['documents'][index])
#     ind_doc_summary['summary_1'] = summary

#     reference = evaluation_dictionary['summary'][2*index]
#     ind_doc_summary['referece_1'] = reference

#     score = rouge.get_scores(summary,reference)[0]
#     results[f'doc_{index+1}_sum_1']=score
    
#     ############################################################
#     summary = getSummary(evaluation_dictionary['documents'][index])
#     ind_doc_summary['summary_2'] = summary

#     reference = evaluation_dictionary['summary'][2*index+1]
#     ind_doc_summary['referece_2'] = reference

#     score = rouge.get_scores(summary,reference)[0]
#     results[f'doc_{index+1}_sum_2']=score
    
#     ###########################################################
#     summary_collection[f'document_{index}'] = ind_doc_summary


# with open('output/summary.json', 'w', encoding="utf-8") as filehandle:
#     json.dump(summary_collection, filehandle)


# with open('output/output.json', 'w') as filehandle:
#     json.dump(results, filehandle)

# avg_result = {
#     "rouge-1":{
#         "r": 0.0,
#         "p": 0.0,
#         "f": 0.0
#     },
#     "rouge-2": {
#         "r": 0.0,
#         "p": 0.0,
#         "f": 0.0
#     },
#     "rouge-l": {
#         "r": 0.0,
#         "p": 0.0,
#         "f": 0.0
#     }
# }
# length = len(results.values())
# for result in results.values():
#     avg_result["rouge-1"]["r"] += (1/length) * result["rouge-1"]["r"]
#     avg_result["rouge-1"]["p"] += (1/length) * result["rouge-1"]["p"]
#     avg_result["rouge-1"]["f"] += (1/length) * result["rouge-1"]["f"]
#     avg_result["rouge-2"]["r"] += (1/length) * result["rouge-2"]["r"]
#     avg_result["rouge-2"]["p"] += (1/length) * result["rouge-2"]["p"]
#     avg_result["rouge-2"]["f"] += (1/length) * result["rouge-2"]["f"]
#     avg_result["rouge-l"]["r"] += (1/length) * result["rouge-l"]["r"]
#     avg_result["rouge-l"]["p"] += (1/length) * result["rouge-l"]["p"]
#     avg_result["rouge-l"]["f"] += (1/length) * result["rouge-l"]["f"]


# with open('output/avg_output.json', 'w') as filehandle:
#     json.dump(avg_result, filehandle)


###########################################################################
##########################################################################
##########################################################################
#########################################################################



# evaluation_dictionary = {}
# with open('datasets/BangladeshNewsData.json','r',encoding='utf-8') as newsjson:
#     evaluation_dictionary = json.load(newsjson)


# results = {}
# results_for_excell={}
# exp_summary = {}
# for index in range(len(evaluation_dictionary)):
#     print(index)
    
#     ind_doc_summary={}
#     ind_doc_summary['document'] = evaluation_dictionary[f'doc_{index+1}']["Description"]
    
#     ############################################################
#     summary = getSummary(evaluation_dictionary[f'doc_{index+1}']["Description"])
#     ind_doc_summary['summary'] = summary
    

#     reference = evaluation_dictionary[f'doc_{index+1}']['Meta-Summary']
#     ind_doc_summary['referece'] = reference

#     if(summary.strip() and reference.strip()):
#         score = rouge.get_scores(summary,reference)[0]
#         results[f'doc_{index+1}']=score
    
#     ###########################################################
#     exp_summary[f'document_{index}'] = ind_doc_summary


# with open('output/summary_BangladeshNewsData.json', 'w', encoding="utf-8") as filehandle:
#     json.dump(exp_summary, filehandle)


# with open('output/rogue_output_BangladeshNewsData.json', 'w') as filehandle:
#     json.dump(results, filehandle)

# avg_result = {
#     "rouge-1":{
#         "r": 0.0,
#         "p": 0.0,
#         "f": 0.0
#     },
#     "rouge-2": {
#         "r": 0.0,
#         "p": 0.0,
#         "f": 0.0
#     },
#     "rouge-l": {
#         "r": 0.0,
#         "p": 0.0,
#         "f": 0.0
#     }
# }
# length = len(results.values())
# for result in results.values():
#     avg_result["rouge-1"]["r"] += (1/length) * result["rouge-1"]["r"]
#     avg_result["rouge-1"]["p"] += (1/length) * result["rouge-1"]["p"]
#     avg_result["rouge-1"]["f"] += (1/length) * result["rouge-1"]["f"]
#     avg_result["rouge-2"]["r"] += (1/length) * result["rouge-2"]["r"]
#     avg_result["rouge-2"]["p"] += (1/length) * result["rouge-2"]["p"]
#     avg_result["rouge-2"]["f"] += (1/length) * result["rouge-2"]["f"]
#     avg_result["rouge-l"]["r"] += (1/length) * result["rouge-l"]["r"]
#     avg_result["rouge-l"]["p"] += (1/length) * result["rouge-l"]["p"]
#     avg_result["rouge-l"]["f"] += (1/length) * result["rouge-l"]["f"]


# with open('output/avg_rogue_output_BangladeshNewsData.json', 'w') as filehandle:
#     json.dump(avg_result, filehandle)



###########################################################################
##########################################################################
##########################################################################
#########################################################################


#reading evaluation dataset
evaluation_dictionary = {}
with open('datasets/BangladeshNewsData.json','r',encoding='utf-8') as newsjson:
    evaluation_dictionary = json.load(newsjson)

sigmas=[0.01, 0.05, 0.1, 0.25, 0.3, 0.4, 0.5, 0.6, 0.75, 0.8, 0.9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 25, 30, 40, 50, 60, 75, 100, 200, 500, 1000]

for sigma in sigmas:
    # unranked
    results = {}
    for index in range(len(evaluation_dictionary)):
        print(f's->{sigma} i-> {index}')
        
        summary = get_summary_unranked_sigma( evaluation_dictionary[f'doc_{index+1}']["Description"] , sigma )
        reference = evaluation_dictionary[f'doc_{index+1}']['Meta-Summary']

        if(summary.strip() and reference.strip()):
            score = rouge.get_scores(summary,reference)[0]
            results[f'doc_{index+1}']=score
    
    with open(f'output/fine_tuning/sigma{sigma}/unranked_output.json', 'w') as filehandle:
        json.dump(results, filehandle)

    # ranked
    for index in range(len(evaluation_dictionary)):
        print(f's->{sigma} i-> {index}')
        
        summary = get_summary_ranked_sigma( evaluation_dictionary[f'doc_{index+1}']["Description"] , sigma )
        reference = evaluation_dictionary[f'doc_{index+1}']['Meta-Summary']

        if(summary.strip() and reference.strip()):
            score = rouge.get_scores(summary,reference)[0]
            results[f'doc_{index+1}']=score
    
    with open(f'output/fine_tuning/sigma{sigma}/ranked_output.json', 'w') as filehandle:
        json.dump(results, filehandle)


