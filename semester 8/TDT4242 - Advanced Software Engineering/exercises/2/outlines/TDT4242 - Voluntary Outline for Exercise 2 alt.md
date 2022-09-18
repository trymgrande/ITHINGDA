Introduction
============

-   Summarize shortly what they have done

Implementation of the new features by using DevOps environment
==============================================================

-   Write about the used DevOps tool.

-   Describe the new features which you are going to implement.

-   Explain the design and implementation of new features.

-   Write about the way of using the DevOps environment to implement the
    new features.

-   Write about meeting with your peer group and their feedback (You
    should also list the participants of each meeting of your group and
    your peer group.)

Automated test scripts to get full statement coverage
=====================================================

-   Summarize the statement coverage results.

-   Provide information about where to find the test scripts in your
    code for running these tests.

Automated test scripts for black-box testing
============================================

-   Summarize the test results

-   Provide information about where to find the test scripts in your
    code for running these tests.

Acceptance tests
================

-   Acceptance test cases and results. The test case template is below

::: {#tab:test_case_1}
  Test Case Name                         Correct PIN entry on first try
  -------------------------------------- -----------------------------------------------------------------
  Test Case ID                           TC-1
  Description                            A customer enters the PIN number correctly on the first attempt
                                         1\. The expected PIN is \"2468\"
                                         2\. Screen 2 is displayed
                                         
  *Input events (performed by tester)*   *Output events (observed by system tester)*
                                         1\. Screen 2 shows '- - - -'
  2\. Touch digit 2                      
                                         3\. Screen 2 shows '- - - \*'
  4\. Customer touches digit 4           
                                         5\. Screen 2 shows '- - \* \*'

  : Test Case 1 - Correct PIN entry on first try
:::
