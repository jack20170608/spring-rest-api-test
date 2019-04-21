Scenario: pending implementation
Given a file named "example_without_block_spec.rb" with:
"""
    describe "an example" do
        it "has not yet been implemented"
    end
    """
When I run "spec example_without_block_spec.rb"
Then the exit code should be 0
And the stdout should include
"""
    Pending:
    an example has not yet been implemented \(Not Yet Implemented\)
    .\/example_without_block_spec.rb:2
    Finished in ([\d\.]*) seconds
    1 example, 0 failures, 1 pending
    """