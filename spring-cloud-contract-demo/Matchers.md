
```
 body:
// your own regex
      - path: $.duck
        type: by_regex
        value: "[0-9]{3}"
// equalTO
      - path: $.duck
        type: by_equality
//regex - predefined: only_alpha_unicode
      - path: $.alpha
        type: by_regex
        predefined: only_alpha_unicode
      - path: $.number
        type: by_regex
        predefined: number
//regex - predefined: any_boolean
      - path: $.aBoolean
        type: by_regex
        predefined: any_boolean
//date
      - path: $.date
        type: by_date
      - path: $.dateTime
        type: by_timestamp

// null value
      - path: $.nullvalue
        type: by_null


      - path: $.valueWithMin
        type: by_type
        minOccurrence: 1
      - path: $.valueWithMax
        type: by_type
        maxOccurrence: 3
      - path: $.valueWithMinMax
        type: by_type
        minOccurrence: 1
        maxOccurrence: 3


    queryParameters:
      - key: limit
        type: equal_to
        value: 20

//contianing
      - key: offset
        type: containing
        value: 20
      - key: sort
        type: equal_to
        value: name

//not matching
      - key: search
        type: not_matching
        value: '^[0-9]{2}$'
```