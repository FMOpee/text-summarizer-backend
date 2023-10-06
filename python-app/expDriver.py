from exp_modules.expService import getSummary
from rouge import Rouge
from common_utils.FileReader import read_files
import json


rouge = Rouge()

# evaluation_dictionary = read_files()
# print(len(evaluation_dictionary['documents']))
# print(len(evaluation_dictionary['summary']))

# results = {}
# results_for_excell={}
# exp_summary = {}
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
#     exp_summary[f'document_{index}'] = ind_doc_summary


# with open('output/exp_summary.json', 'w', encoding="utf-8") as filehandle:
#     json.dump(exp_summary, filehandle)


# with open('output/exp_output.json', 'w') as filehandle:
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


# with open('output/avg_exp_output.json', 'w') as filehandle:
#     json.dump(avg_result, filehandle)


###################################################################################################################
###################################################################################################################
###################################################################################################################
###################################################################################################################


# evaluation_dictionary = {}
# with open('datasets/allNews.json','r',encoding='utf-8') as newsjson:
#     evaluation_dictionary = json.load(newsjson)


# results = {}
# results_for_excell={}
# # exp_summary = {}


# for index in range(12400):
#     results[f'doc_{index+1}']={}

#     for ncluster in range(2,9):
#         if index == 8112 or index == 12483:
#             pass
#         else:
#             ind_doc_summary={}
#             ind_doc_summary['document'] = evaluation_dictionary[f'doc_{index+1}']["Description"]
            
#             ############################################################
#             summary = getSummary(evaluation_dictionary[f'doc_{index+1}']["Description"] , n_cluster=ncluster)
#             # ind_doc_summary['summary'] = summary
            

#             reference = evaluation_dictionary[f'doc_{index+1}']['Meta-Summary']
#             # ind_doc_summary['referece'] = reference

#             if(summary.strip() and reference.strip()):
#                 score = rouge.get_scores(summary,reference)[0]
#                 results[f'doc_{index+1}'][ncluster]=score
            

#             print(f'{index} - {ncluster}')
#             ###########################################################
#             # exp_summary[f'document_{index}'] = ind_doc_summary


# # with open('output/exp_summary_allNewsData.json', 'w', encoding="utf-8") as filehandle:
# #     json.dump(exp_summary, filehandle)


# with open('output/exp_rogue_output_allNewsData.json', 'w') as filehandle:
#     json.dump(results, filehandle)

# # avg_result = {
# #     "rouge-1":{
# #         "r": 0.0,
# #         "p": 0.0,
# #         "f": 0.0
# #     },
# #     "rouge-2": {
# #         "r": 0.0,
# #         "p": 0.0,
# #         "f": 0.0
# #     },
# #     "rouge-l": {
# #         "r": 0.0,
# #         "p": 0.0,
# #         "f": 0.0
# #     }
# # }
# # length = len(results.values())
# # for result in results.values():
# #     avg_result["rouge-1"]["r"] += (1/length) * result["rouge-1"]["r"]
# #     avg_result["rouge-1"]["p"] += (1/length) * result["rouge-1"]["p"]
# #     avg_result["rouge-1"]["f"] += (1/length) * result["rouge-1"]["f"]
# #     avg_result["rouge-2"]["r"] += (1/length) * result["rouge-2"]["r"]
# #     avg_result["rouge-2"]["p"] += (1/length) * result["rouge-2"]["p"]
# #     avg_result["rouge-2"]["f"] += (1/length) * result["rouge-2"]["f"]
# #     avg_result["rouge-l"]["r"] += (1/length) * result["rouge-l"]["r"]
# #     avg_result["rouge-l"]["p"] += (1/length) * result["rouge-l"]["p"]
# #     avg_result["rouge-l"]["f"] += (1/length) * result["rouge-l"]["f"]


# # with open('output/avg_exp_rogue_output_allNewsData.json', 'w') as filehandle:
# #     json.dump(avg_result, filehandle)




####################################################################################################################
###################################################################################################################################
############################################################################################################################
#######################################################################################################################


evaluation_dictionary = {}
with open('datasets/BangladeshNewsData.json','r',encoding='utf-8') as newsjson:
    evaluation_dictionary = json.load(newsjson)


results = {}
results_for_excell={}
exp_summary = {}
for index in range(len(evaluation_dictionary)):
    print(index)
    
    ind_doc_summary={}
    ind_doc_summary['document'] = evaluation_dictionary[f'doc_{index+1}']["Description"]
    
    ############################################################
    summary = getSummary(evaluation_dictionary[f'doc_{index+1}']["Description"])
    ind_doc_summary['summary'] = summary
    

    reference = evaluation_dictionary[f'doc_{index+1}']['Meta-Summary']
    ind_doc_summary['referece'] = reference

    if(summary.strip() and reference.strip()):
        score = rouge.get_scores(summary,reference)[0]
        results[f'doc_{index+1}']=score
    
    ###########################################################
    exp_summary[f'document_{index}'] = ind_doc_summary


with open('output/exp_summary_BangladeshNewsData_withRanking.json', 'w', encoding="utf-8") as filehandle:
    json.dump(exp_summary, filehandle)


with open('output/exp_rogue_output_BangladeshNewsData_withRanking.json', 'w') as filehandle:
    json.dump(results, filehandle)

avg_result = {
    "rouge-1":{
        "r": 0.0,
        "p": 0.0,
        "f": 0.0
    },
    "rouge-2": {
        "r": 0.0,
        "p": 0.0,
        "f": 0.0
    },
    "rouge-l": {
        "r": 0.0,
        "p": 0.0,
        "f": 0.0
    }
}
length = len(results.values())
for result in results.values():
    avg_result["rouge-1"]["r"] += (1/length) * result["rouge-1"]["r"]
    avg_result["rouge-1"]["p"] += (1/length) * result["rouge-1"]["p"]
    avg_result["rouge-1"]["f"] += (1/length) * result["rouge-1"]["f"]
    avg_result["rouge-2"]["r"] += (1/length) * result["rouge-2"]["r"]
    avg_result["rouge-2"]["p"] += (1/length) * result["rouge-2"]["p"]
    avg_result["rouge-2"]["f"] += (1/length) * result["rouge-2"]["f"]
    avg_result["rouge-l"]["r"] += (1/length) * result["rouge-l"]["r"]
    avg_result["rouge-l"]["p"] += (1/length) * result["rouge-l"]["p"]
    avg_result["rouge-l"]["f"] += (1/length) * result["rouge-l"]["f"]


with open('output/avg_exp_rogue_output_BangladeshNewsData_withRanking.json', 'w') as filehandle:
    json.dump(avg_result, filehandle)