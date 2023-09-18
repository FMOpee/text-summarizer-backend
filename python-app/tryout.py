import nltk
nltk.download('punkt')
import re
from nltk.tokenize import RegexpTokenizer

def split_text_by_dividers(text, dividers):
    # Create a regular expression pattern to match the dividers
    divider_pattern = '|'.join(map(re.escape, dividers))
    
    # Use the RegexpTokenizer to split the text based on the dividers
    tokenizer = RegexpTokenizer(f'[^{divider_pattern}]+|[{divider_pattern}]')
    tokens = tokenizer.tokenize(text)
    
    # Remove any empty strings from the list of tokens
    tokens = [token.strip() for token in tokens if (token.strip() and token not in dividers)]
    
    return tokens

# Example usage
text = """আলোচিত এই অ্যাপের নাম টিকটক। ভিডিও তৈরি ও শেয়ার করার অ্যাপ। অত্যন্ত জনপ্রিয়। বিশেষ করে তরুণদের কাছে।
সম্প্রতি চীনের তৈরি এই অ্যাপটি নিয়ে শুরু হয়েছে রাজনীতি।
প্র্রেসিডেন্ট ট্রাম্পের হুমকি
প্রেসিডেন্ট ডোনাল্ড টাম্প যুক্তরাষ্ট্রে টিকটকের অপারেশন বন্ধ করে দেওয়ার হুমকি দিচ্ছেন। কিন্তু মার্কিন কোম্পানি মাইক্রোসফ্ট এটি কেনার জন্য দরকষাকষি চালিয়ে যাচ্ছে।
মি. ট্রাম্প এখন তার কথার সুর কিছুটা পরিবর্তন করে বলছেন, আমেরিকান কোন প্রতিষ্ঠান যদি শেষ পর্যন্ত যুক্তরাষ্ট্রে টিকটকের ইউনিট ক্রয় করে তাহলে এই বিক্রি থেকে তার সরকারের একটা ভাগ দিতে হবে।
প্রেসিডেন্ট ট্রাম্প বলেছেন, মাইক্রোসফ্টের প্রধানকে টেলিফোন করে তিনি এই অর্থ দাবি করেছেন।
তিনি হুঁশিয়ার করে দিয়েছেন কোন সমঝোতা না হলে তিনি চীনের বাইটড্যান্স মালিকানাধীন এই অ্যাপটি ১৫ই সেপ্টেম্বরে নিষিদ্ধ করে দেবেন।
টিকটকের বিরুদ্ধে ট্রাম্প প্রশাসনের অভিযোগ হচ্ছে এই অ্যাপটি চীনা সরকারের কাছে তথ্য পাচার করছে। বেইজিং এবং টিকটক উভয়েই এই অভিযোগ অস্বীকার করেছে।
প্রেসিডেন্ট ট্রাম্পের এই অর্থ দাবির সমালোচনা করছেন অনেকে। তারা বলছেন এর ফলে টিকটক ও মাইক্রোসফ্টের মধ্যে সমঝোতার পথ আরো কঠিন হয়ে পড়বে।
যুক্তরাষ্ট্রে টিকটক নিষিদ্ধ করার হুমকি দিচ্ছেন প্রেসিডেন্ট ট্রাম্প।
একজন আইনজীবী নিকোলাস ক্লেইন বলছেন, বেসরকারি কোন সমঝোতা থেকে সরকারের অর্থ দাবি করার কোন এখতিয়ার নেই।
কিন্তু প্রশ্ন হচ্ছে কী কারণে চীনা এই অ্যাপটি সারা বিশ্বে এতোটা জনপ্রিয় ও সফল হতে পারলো?
ভাইরাল হওয়া মজার মজার নাচ ও ঠোঁট মেলানো হাস্যকৌতুকের ভিডিও তৈরি ও শেয়ার হয় এই টিকটক অ্যাপে।
অল্প বয়সী ছেলেমেয়েদর কাছে এই অ্যাপ খুবই জনপ্রিয়।
এর মাধ্যমে ব্যবহারকারীর চাহিদা মতো যে কোন কিছুর সাথে নিজের ঠোঁট মিলিয়ে ছোট ছোট ভিডিও তৈরি করে তা শেয়ার করা যায়।
আবার এর সাহায্যে নিজের পছন্দের গানের সাথে নাচ বা নানা ধরনের কমেডিও তৈরি করা সম্ভব। স্টিকার, ফিল্টার ও অগম্যান্টেড রিয়েলিটিও ব্যবহার করা যায় এসব ভিডিওতে।
ফ্রি এই অ্যাপটিকে ভিডিও শেয়ারিং অ্যাপ ইউটিউবের ছোটখাটো সংস্করণ বলা চলে।
টিকটক ব্যবহারকারীরা এখানে এক মিনিট লম্বা ভিডিও পোস্ট করতে পারেন এবং এখানকার বিশাল তথ্যভাণ্ডার থেকে গান ‍ও ফিল্টার বাছাই করতে পারেন।
টিকটক দিয়ে অল্পবয়সী ছেলে-মেয়েরা পরিচিত ফিল্মী ডায়লগ বা গানের সঙ্গে নিজেরা অভিনয় করে মজার মজার ভিডিও তৈরি করে থাকে।
একজন ব্যবহারকারীর যখন এক হাজারের বেশি ফলোয়ার হয় তখন তিনি তার ভক্তদের জন্য লাইভে আসতে পারেন।
শুধু তাই নয়, এখানে তিনি ডিজিটাল উপহারও গ্রহণ করতে পারেন যা অর্থের সাথেও বিনিময় করা যায়।
একজন ব্যবহারকারী যাকে অনুসরণ করেন তিনি তার ভিডিও দেখতে পারেন। এছাড়াও তিনি আগে যেসব বিষয়ে ভিডিও দেখেছেন তার ওপর ভিত্তি করেও তিনি নতুন নতুন ভিডিও দেখতে পারেন।
ব্যবহারকারীরা নিজেদের মধ্যেও ব্যক্তিগত বার্তা আদান প্রদান করতে পারেন এই অ্যাপের মাধ্যমে।
যারা কিছুটা অভিনয় করেন বা কমেডি করতে পারেন, তাদের নিজেদের প্রতিভা তুলে ধরার জন্য এই টিকটক একটি নতুন প্ল্যাটফর্ম হিসেবে উঠে এসেছে।
মজার মজার ভিডিও তৈরি ও শেয়ার করা যায় এই টিকটকে যা তরুণদের কাছে অত্যন্ত জনপ্রিয়।
টিকটকের জনপ্রিয়তার পেছনে কারণগুলো হচ্ছে:
    এসব ভিডিও আকারে ছোট
    ব্যবহার করা সহজ
    সবসময় এতে যুক্ত হয় নতুন নতুন ফিচার
    ভিডিওতে নিজের কণ্ঠ মেলানো যাকে বলা হয় লিপ সিঙ্ক
    ভিডিওতে অন্যের কণ্ঠ ব্যবহার
২০১৯ সালের শুরু থেকেই ডাউনলোড চার্টের শীর্ষের কাছাকাছি অবস্থান করছে টিকটক।
গত বছর ইন্সটাগ্রাম ও স্ন্যাপচ্যাটকে টপকে বিশ্বের চতুর্থ সর্বোচ্চ ডাউনলোডকারী অ্যাপে পরিণত হয় এটি।
এই কোম্পানির মূল্য দাঁড়িয়েছে ৭৫ বিলিয়ন ডলার যা রাইড শেয়ারিং অ্যাপ উবারের চেয়েও বেশি।
করোনাভাইরাস সঙ্কটের সময় টিকটকের জনপ্রিয়তা খুব দ্রুত বৃদ্ধি পেতে থাকে। লকডাউনের কারণে ঘরবন্দী মানুষের তীব্র আগ্রহ তৈরি হয় এই অ্যাপের প্রতি।
বলা হচ্ছে, টিকটক ও তার সহযোগী অ্যাপ দোইনের মোট ব্যবহারকারীর সংখ্যা প্রায় ৮০ কোটি। দোইন অ্যাপটি শুধু চীনেই ব্যবহার করা যায়।
জাতীয় নিরাপত্তার কারণ দেখিয়ে ভারতে টিকটক নিষিদ্ধ করার আগে এটি সেখানে ব্যাপক জনপ্রিয় হয়ে উঠেছিল। সেদেশে দশ কোটিরও বেশি মানুষ ইতোমধ্যে টিকটক ডাউনলোড করেছে।
২০১৮ সালে আমেরিকায় সবচেয়ে বেশি ডাউনলোড করা অ্যাপ ছিল এই টিকটক।
ইকোনমিক টাইমস পত্রিকা লিখছে, প্রতিমাসে গড়ে প্রায় দুই কোটি মানুষ টিকটক ব্যবহার করছে।"""

# Define a list of dividers you want to use
dividers = ['।', '|', '!', '\n', '?',":"]

tokens = split_text_by_dividers(text, dividers)
for idx, sentence in enumerate(tokens):
        print(f"Sentence {idx + 1}: {sentence}")
print(tokens)
